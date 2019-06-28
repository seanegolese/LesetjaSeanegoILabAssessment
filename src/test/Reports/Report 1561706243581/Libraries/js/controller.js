angular.module('katanaResult', []).controller('ResultsController', function ($scope, $http) {
    $scope.resultsData = data;
    $scope.settings = settings
    $scope.uploaded = false;
    $scope.showPassed = true;
    $scope.showFailed = true;
    $scope.joke_text = "";
    $scope.joke_submitter = "";
    $scope.uploadText = "";
    $scope.imagesrc = 'data:image/jpg;base64,';
    $scope.modalText = "You are about to upload these results to the Katana Automation Server. Please confirm.";
    $scope.aboutModalText = 'This is an auto generated report from the Katana Test Automation Framework. If you find any bugs, or would like to see additional information/features added to these reports, please contact Shamir Gangai.'
    getJoke();
    getUploadStatus();

    $scope.showImage = function (img) {
        $scope.imagesrc = "data:image/jpg;base64," + img;
        document.getElementById('largeImgPanel').style.visibility = 'visible';
        if (document.selection) document.selection.empty();
        if (window.getSelection) window.getSelection().removeAllRanges();
    }

    $scope.hideImagePanel = function () {
        document.getElementById('largeImgPanel').style.visibility = 'hidden';
    }

    $scope.DownloadImages = function () {
        var zip = new JSZip();
        var imgFolder;
        angular.forEach($scope.resultsData.TestCases, function (tsvalue, tskey) {
            imgFolder = zip.folder(tsvalue.Name);
            angular.forEach(tsvalue.TestSteps, function (tcvalue, tckey) {
                angular.forEach(tcvalue.Screenshot, function (scavalue, scakey) {
                    angular.forEach(scavalue, function (scvalue, sckey) {
                        imgFolder.file(tckey + "." + scakey + "-" + tcvalue.Description + ".png", scavalue['Screenshots'], { base64: true });
                    })
                })
            })
        });

        zip.generateAsync({ type: "blob" }).then(function (content) {
            saveAs(content, $scope.resultsData.Name + ".zip");
        });
    }

    $scope.ExpandAll = function (mode) {
        if (mode == "expand") {
            $('.collapse').collapse("show");
            return;
        }
        $('.collapse').collapse("hide");
    }

    $scope.setPassFilter = function () {
        $scope.showPassed = !$scope.showPassed;
    }

    $scope.setFailFilter = function () {
        $scope.showFailed = !$scope.showFailed;
    }

    $scope.Confirm = function () {
        Upload();
    }

    function getJoke() {
        var jsonp_url = "http://www.laughfactory.com/home/getJokeoftheDay/?callback=?";
        $.getJSON(jsonp_url, function (data) {
            if (data) {
                $scope.$apply(function () {
                    $scope.joke_text = data.joke_text;
                    $scope.joke_submitter = data.joke_submitter;
                });
            }
        });
    }

    function Upload() {
        $scope.modalText = "Uploading... This may take a while. This window will close once the upload is complete";
        $http.post($scope.settings.ServerUrl, $scope.resultsData).then(function (data) {
            $('#confirmModal').modal('hide');
            getUploadStatus();
        });
    }

    function getUploadStatus() {
        $http.get($scope.settings.ServerUrl + '/?TestSuiteID=' + $scope.resultsData.Id).success(function (data) {
            $scope.uploadText = "Uploaded to Katana";
            $('#uploadContainer').addClass('disabled');
            $('#uploadContainer').attr('data-toggle').value = "";
            $('#uploadContainer').attr('data-toggle', "");
        }).error(function (data) {
            $scope.uploadText = "Not uploaded to Katana";
            $('#uploadContainer').removeClass('disabled');
            $('#uploadContainer').attr('data-toggle', "modal");
        });
    }
});