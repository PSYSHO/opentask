var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http,MainService) {
    let num;
    $scope.games=[]
    $scope.game={
        id:"",
        number:""
    }
    $scope.attempts =[]
    $scope.attempt={
        id:"1",
        result: "",
        game:"",
        User:"",
        flat:""
    }
    MainData()
    function MainData() {
        $http({
            method: 'GET',
            url: './game'
        }).then(
            function (res) { // success
                $scope.attempts = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.setAnswer = function (answer) {
        if ($scope.answer != null ) {
            MainService.setAnswer(answer)
                .then(MainData)
                .then (function success(response){
                        $scope.message = 'Attempt is counted!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding Attempt!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a number!';
            $scope.message = '';
        }
    }
    $scope.gameStart = function () {
            MainService.gameStart()
    }

})
app.service('MainService', ['$http', function ($http) {

    this.setAnswer = function setAnswer(answer) {
        return $http({
            method: 'GET',
            url: './game/'+answer,
        });
    }
    this.gameStart = function gameStart() {
        $http({
            method: 'POST',
            url: './game/start'
        })
    }

}]);