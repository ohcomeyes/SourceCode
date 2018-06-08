var app = angular.module("loginApp", []);

app.controller("loginController", function($scope,$http) {
    ///// TRANSFORM CHECKBOX /////
    jQuery('input:checkbox').uniform();

    ///// LOGIN FORM SUBMIT /////
    // Submit form
    $scope.login = function() {
        if(jQuery('#username').val() == '' && jQuery('#password').val() == '') {
            jQuery('.nousername').fadeIn();
            jQuery('.nopassword').hide();
            return false;
        }
        if(jQuery('#username').val() != '' && jQuery('#password').val() == '') {
            jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(jQuery('#username').val());
            jQuery('.nousername,.username').hide();
            return false;
        }
        //angluar 使用其它插件时候 需要在调用插件后使用$scope.$apply()刷新表单绑定数据，测试暂时赋值
		$http({
			method: 'POST',
			url: 'system/login',
			data:$scope.user
		}).then(function successCallback(response) {
			if(response.data.code == "200"){
                window.location.href = "index.html";
			}else{
                layer.msg('密码或帐号错误', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
			}
		}, function errorCallback(response) {
                // 请求失败执行代码
			layer.msg('登录失败，请联系系统管理员', {
				icon: 2,
				time: 1000 //1s后自动关闭
			});
		});
    };


    // jQuery('#login').submit(function(){
    //
    //     if(jQuery('#username').val() == '' && jQuery('#password').val() == '') {
    //         jQuery('.nousername').fadeIn();
    //         jQuery('.nopassword').hide();
    //         return false;
    //     }
    //     if(jQuery('#username').val() != '' && jQuery('#password').val() == '') {
    //         jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(jQuery('#username').val());
    //         jQuery('.nousername,.username').hide();
    //         return false;;
    //     }
    // });

    ///// ADD PLACEHOLDER /////
    jQuery('#username').attr('placeholder','Username');
    jQuery('#password').attr('placeholder','Password');
});

