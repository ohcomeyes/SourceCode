/**
 * 
 */
var mainApp=angular.module("mainApp",['ui.router','ngDialog']);

mainApp.run(function ($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams) {
        // to be used for back button //won't work when page is reloaded.
        $rootScope.previousState_name = fromState.name;
        $rootScope.previousState_params = fromParams;
    });
    //back button function called from back button's ng-click="back()"
    $rootScope.back = function () {//实现返回的函数
        // $state.go($rootScope.previousState_name, $rootScope.previousState_params);
        history.back();
    };
});

//截断字符串长度
mainApp.filter('textLengthSet', function() {
    return function(value, wordwise, max, tail) {
        if (!value) return '';
        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;
        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
                value = value.substr(0, lastspace);
            }
        }
        return value + (tail || ' …');//'...'可以换成其它文字
    };
});

mainApp.config(function($locationProvider,$stateProvider,$urlRouterProvider){
    $locationProvider.hashPrefix('');
    var dashboardState = {
        name: 'dashboard',
        url: '/dashboard',
        views:{
            '':{
                templateUrl:'index-left.html'
            },
            'indexView@dashboard':{
                templateUrl:'dashboard-demand.html',
                controller:'dashboardController'
            }
        }
    }

    var dIndexState = {
        name: 'dIndex',
        url: '/dIndex',
        views:{
            '':{
                templateUrl:'index-left.html'
            },
            'indexView@dIndex':{
                templateUrl:'dashboard.html',
                controller:'dIndexController'
            }
        }
    }

    var dUserState = {
        name: 'dUser',
        url: '/dUser',
        views:{
            '':{
                templateUrl:'index-left.html'
            },
            'indexView@dUser':{
                templateUrl:'dashboard-user.html',
                controller:'duController'
            }
        }
    }

    //user
    var usersState = {
        name: 'users',
        url: '/users',
        views:{
            '':{
                templateUrl:'users-left.html'
            },
            'userList@users':{
                templateUrl:'users.html',
                controller:'userController'
            }
        }
    }
    var levelState = {
        name: 'level',
        url: '/level',
        views:{
            '':{
                templateUrl:'users-left.html'
            },
            'userList@level':{
                templateUrl:'users-level.html'
            }
        }
    }
    var usersSpecimenState = {
        name: 'usersSpecimen',
        url: '/usersSpecimen',
        views:{
            '':{
                templateUrl:'users-left.html'
            },
            'userList@usersSpecimen':{
                templateUrl:'users-specimen.html',
                controller:'usersSpecimenController'
            }
        }
    }
    var usersSpecimenFormState = {
        name: 'usersSpecimen.forms',
        url: '/usersSpecimenForms/:id',
        views:{
            'userList@usersSpecimen':{
                templateUrl:'user-specimen-form.html',
                controller:'userSpecimenFormController'
            }
        }
    }

    var userState ={
        name: 'users.user',
        url: '/user',
        views:{
            'userList@users':{
                templateUrl:'user.html'
            }
        }
    }

    // demand forms
    var demandState = {
        name: 'demand',
        url: '/demand',
        views:{
            '':{
            	templateUrl:'demand-left.html'
            },
            'demandView@demand':{
            	templateUrl:'demand.html',
				controller:'demandController'
            }
        }
    }
    var industryState = {
        name: 'industry',
        url: '/industry',
        views:{
            '':{
                templateUrl:'demand-left.html'
            },
            'demandView@industry':{
                templateUrl:'industry.html'
            }
        }
    }
    var tradeState = {
        name: 'trade',
        url: '/trade',
        views:{
            '':{
                templateUrl:'demand-left.html'
            },
            'demandView@trade':{
                templateUrl:'trade.html'
            }
        }
    }
    var costState = {
        name: 'cost',
        url: '/cost',
        views:{
            '':{
                templateUrl:'demand-left.html'
            },
            'demandView@cost':{
                templateUrl:'cost.html',
                controller:'costController'
            }
        }
    }
    var costFormState = {
        name: 'cost.forms',
        url: '/costForm/:id',
        views:{
            'demandView@cost':{
                templateUrl:'cost-form.html',
                controller:'costFormController'
            }
        }
    }
    var demandFormState = {
        name: 'demand.forms',
        url: '/demandForms/:demand_id/:status',
        views:{
            'demandView@demand':{
                templateUrl:'forms.html',
                controller:'demandFormController'
            }
        }
    }
    var demandUserState = {
        name: 'demand.user',
        url: '/demandUser/:demand_id',
        views:{
            'demandView@demand':{
                templateUrl:'demand-user.html',
                controller:'demandUserController'
            }
        }
    }
    var demandSupportState = {
        name: 'demand.user.support',
        url: '/support',
        views:{
            'demandView@demand':{
                templateUrl:'support.html'
            }
        }
    }
    var demandImageState = {
        name: 'demand.user.image',
        url: '/image',
        views:{
            'demandView@demand':{
                templateUrl:'image.html'
            }
        }
    }
    //design
    var designState = {
        name: 'design',
        url: '/design',
        views:{
            '':{
                templateUrl:'design-left.html'
            },
            'designView@design':{
                templateUrl:'design.html',
                controller:'designController'
            }
        }
    }
    var designFormState = {
        name: 'design.forms',
        url: '/designForms/:demand_id/:status',
        views:{
            'designView@design':{
                templateUrl:'forms.html',
                controller:'demandFormController'
            }
        }
    }
    var designUserState ={
        name: 'design.user',
        url: '/designUser',
        views:{
            'designView@design':{
                templateUrl:'user.html'
            }
        }
    }

    //specimen
    var specimenState = {
        name: 'specimen',
        url: '/specimen',
        views:{
            '':{
                templateUrl:'specimen-left.html'
            },
            'specimenView@specimen':{
                templateUrl:'specimen.html',
                controller:'specimenController'
            }
        }
    }

    var specimenFormState = {
        name: 'specimen.forms',
        url: '/specimenForms/:demand_id/:status',
        views:{
            'specimenView@specimen':{
                templateUrl:'forms.html',
                controller:'demandFormController'
            }
        }
    }
    var specimenUserState ={
        name: 'specimen.user',
        url: '/specimenUser',
        views:{
            'specimenView@specimen':{
                templateUrl:'user.html'
            }
        }
    }


    var messagesState = {
        name: 'messages',
        url: '/messages',
        templateUrl:'messages.html'
    }
    var reportsState = {
        name: 'reports',
        url: '/reports',
        templateUrl:'reports.html'
    }
    var notfoundState = {
        name: 'notfound',
        url: '/notfound',
        templateUrl:'notfound.html'
    }
    var formsState = {
        name: 'forms',
        url: '/forms',
        templateUrl:'forms.html'
    }
    $urlRouterProvider.otherwise("/dIndex");
    $stateProvider.state(dashboardState).state(dIndexState).state(dUserState).state(usersState).state(levelState).state(usersSpecimenState).state(usersSpecimenFormState).state(userState).state(demandState).state(demandFormState).state(demandUserState)
        .state(industryState).state(tradeState).state(costState).state(costFormState).state(demandSupportState).state(demandImageState).state(designState).state(designFormState).state(designUserState)
        .state(specimenState).state(specimenFormState).state(specimenUserState).state(messagesState).state(reportsState).state(notfoundState).state(formsState);
});
/**
 * all top
 */
mainApp.controller('indexController', function ($scope,$http){
    $http({
        method: 'GET',
        url: 'api/index/getTotal'
    }).then(function successCallback(response) {
        // $scope.demandAnalyze = response.data;
        $scope.demand_money = response.data.demand_money;
        $scope.demand_count = response.data.demand_count;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
});
/**
 * index
 */
mainApp.controller('dashboardController',function ($scope,$http) {
    ///// DATE PICKER /////
    jQuery( "#datepickfrom, #datepickto" ).datepicker({
        //在这里进行插件的属性设置
        dateFormat:"yy-mm-dd"
    });
    jQuery('#overviewselect, input:checkbox').uniform();
    $http({
        method: 'GET',
        url: 'api/index/getCountPendingWork'
    }).then(function successCallback(response) {
        $scope.demandPending = response.data[0];
        $scope.designPending = response.data[1];
        $scope.specimenPending = response.data[2];
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    //需求分析
    var datepickfrom,datepickto;
    datepickfrom = new Date().Format("yyyy-MM-dd");
    datepickto = new Date().Format("yyyy-MM-dd");
    jQuery("#datepickfrom").val(datepickfrom);
    jQuery("#datepickto").val(datepickto);
    //搜索
    $scope.searchDate = function () {
        datepickfrom = jQuery("#datepickfrom").val();
        datepickto = jQuery("#datepickto").val();
        var sDate = new Date(datepickfrom);
        var eDate = new Date(datepickto);
        if(eDate.getTime() < sDate.getTime()){
            layer.msg('结束日期需大于开始日期', {
                time: 1000, //1s后自动关闭
            });
        }else{
            demandCount($scope,$http,datepickfrom,datepickto);
            demandAnalyze(datepickfrom,datepickto);
        }
    }
    demandCount($scope,$http,datepickfrom,datepickto);
    demandAnalyze(datepickfrom,datepickto);
    demandProportion();
    topFiveDemand($scope,$http);
})
mainApp.controller('duController',function ($scope,$http) {
    // $scope.dashboard = true;
    ///// DATE PICKER /////
    jQuery( "#datepickfrom, #datepickto" ).datepicker();
    jQuery('#overviewselect, input:checkbox').uniform();
    userAnalyze();
})

/**
 * index
 */
mainApp.controller('dIndexController',function ($scope,$http) {
    budgetOutlays();
})

/***
 * user manage
 */
mainApp.controller('userController', function($scope, $http,$location, $anchorScroll) {
    $http({
        method: 'GET',
        url: 'api/users'
    }).then(function successCallback(response) {
        $scope.user = response.data._embedded.users;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $scope.gotoBottom = function() {
        alert(1);
        // 将location.hash的值设置为
        // 你想要滚动到的元素的id
        $location.hash('basics');
        // 调用 $anchorScroll()
        $anchorScroll();
    };


    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });
});


mainApp.controller('usersSpecimenController', function($scope, $http) {
    $http({
        method: 'GET',
        url: 'api/users/getAllUserSpecimen'
    }).then(function successCallback(response) {
        $scope.userSpecimens = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });
});
mainApp.controller('userSpecimenFormController',function($scope,$http,$stateParams){
    $scope.skillList = [{ id: "1", checked:false, name:"工业打板" }, { id: "2",checked:false, name:"版型设计" },
        {id:"3",checked:false,name:"KT板"}];
    // layui form 渲染
    layui.use('form', function(){
        var form = layui.form;
        //加载数据
        if($stateParams.id != null && $stateParams.id != ""){
            $http({
                method: 'GET',
                url: 'api/users/getUserSpecimen?id='+$stateParams.id
            }).then(function successCallback(response) {
                $scope.userSpecimens = response.data;
                $scope.skillList = $scope.userSpecimens.skillList;
            }, function errorCallback(response) {
                // 请求失败执行代码
            });
        }
        $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
            //数据加载完之后
            form.render("checkbox");//更新渲染
            form.render("radio");
        });
        //各种基于事件的操作，下面会有进一步介绍
        form.on('submit(organization)', function(data){
            // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            var adept ="";
            for(var i =0;i<$scope.skillList.length;i++){
                var id = $scope.skillList[i].id;
                if(eval("data.field.adept"+id)=="on"){
                    if(adept == ""){
                        adept+=id;
                    }else{
                        adept+="#"+id;
                    }
                    $scope.skillList[i].checked = true;
                }
            }
            $scope.userSpecimens.skillList =  $scope.skillList;
            $scope.userSpecimens.adept = adept;
            layer.confirm('您是否确定保存信息？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $http({
                    method: 'POST',
                    url: 'api/userSpecimen',
                    data:$scope.userSpecimens
                }).then(function successCallback(response) {
                    layer.msg('保存成功', {
                        icon: 1,
                        time: 1000 //1s后自动关闭
                    },function () {
                        history.back();
                    });
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    layer.msg('保存失败，请联系系统管理员', {
                        icon: 2,
                        time: 1000 //1s后自动关闭
                    });
                });
            }, function(){
                layer.msg('已取消', {
                    time: 1000, //1s后自动关闭
                });
            });
            // return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        // form.on('checkbox(kt)', function(data){
        //     alert(5);
        //     console.log(data.elem); //得到checkbox原始DOM对象
        //     console.log(data.elem.checked); //是否被选中，true或者false
        //     console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        //     console.log(data.othis); //得到美化后的DOM对象
        // });
    });
})


/**
 * demand manage
 */
mainApp.controller('costController',function ($scope,$http) {
    $http({
        method: 'GET',
        url: 'api/costs'
    }).then(function successCallback(response) {
        $scope.cost = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });
})

mainApp.controller('costFormController',function ($scope,$http,$stateParams) {
    // layui form 渲染
    layui.use('form', function(){
        var form = layui.form;
        //加载数据
        if($stateParams.id != null && $stateParams.id != ""){
            $http({
                method: 'GET',
                url: 'api/costs/'+$stateParams.id
            }).then(function successCallback(response) {
                $scope.cost = response.data;
                jQuery("select[name='cost_type']").val(response.data.cost_type);
                jQuery("input:radio[name='cost_method'][value='"+response.data.cost_method+"']").prop('checked','checked');
                form.render();
            }, function errorCallback(response) {
                // 请求失败执行代码
            });
        }
        //监听指定开关
        form.on('switch(switchTest)', function(data){
            // layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
            //     offset: '6px'
            // });
            // layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });
        //各种基于事件的操作，下面会有进一步介绍
        form.on('submit(organization)', function(data){
            // console.log(data.field);
            $scope.cost = data.field;
            console.log($scope.cost);
            // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            layer.confirm('您是否确定保存信息？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $http({
                    method: 'POST',
                    url: 'api/costs/addCost',
                    data:$scope.cost
                }).then(function successCallback(response) {
                    layer.msg('保存成功', {
                        icon: 1,
                        time: 1000 //1s后自动关闭
                    },function () {
                        history.back();
                    });
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    layer.msg('保存失败，请联系系统管理员', {
                        icon: 2,
                        time: 1000 //1s后自动关闭
                    });
                });
            }, function(){
                layer.msg('已取消', {
                    time: 1000, //1s后自动关闭
                });
            });
            // return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        // form.on('checkbox(kt)', function(data){
        //     alert(5);
        //     console.log(data.elem); //得到checkbox原始DOM对象
        //     console.log(data.elem.checked); //是否被选中，true或者false
        //     console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        //     console.log(data.othis); //得到美化后的DOM对象
        // });
    });
})


mainApp.controller('demandController',function ($scope, $http){
    $http({
        method: 'GET',
        url: 'api/demands/findListByDemand'
    }).then(function successCallback(response) {
        $scope.demand = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });

    //启用
    $scope.using = function(demand_id) {
        layer.confirm('您是否确定启用该设计？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $http({
                method: 'GET',
                url: 'api/demands/usingDemandDesign',
                params:{demand_id:demand_id}
            }).then(function successCallback(response) {
                layer.msg('启用成功', {
                    icon: 1,
                    time: 1000 //1s后自动关闭
                },function () {
                    // $state.go("demand");
                    history.back();
                    location.reload();
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
                layer.msg('启用失败，请联系系统管理员', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
            });
        }, function(){
            layer.msg('已取消', {
                time: 1000, //1s后自动关闭
            });
        });
    };
});

mainApp.controller('demandFormController',function ($scope,$http,$location,$anchorScroll,$stateParams,$rootScope) {
    //表单
    $rootScope.data = {
        current: "0" //
    };
    $rootScope.actions = {
        setCurrent: function (param) {
            $rootScope.data.current = param;
        }
    }
    // layui form 渲染
    layui.use(['form','upload','laydate'], function(){
        var form = layui.form
            ,laydate = layui.laydate;
        var $ = layui.jquery
            ,upload = layui.upload;
        laydate.render({
            elem: '#end_date'
        });
        //加载数据
        if($stateParams.demand_id != null && $stateParams.demand_id != ""){
            $http({
                method: 'GET',
                url: 'api/demands/'+$stateParams.demand_id
            }).then(function successCallback(response) {
                $scope.demand = response.data;
                jQuery("select[name='demand_type']").val(response.data.demand_type);
                jQuery("#end_date").val(response.data.end_date);
                jQuery("input:radio[name='demand_making_send'][value='"+response.data.demand_making_send+"']").prop('checked','checked');
                jQuery("input:radio[name='demand_cost'][value='"+response.data.demand_cost+"']").prop('checked','checked');
                jQuery("select[name='demand_cost_id']").val(response.data.demand_cost_id);
                form.render();
                ctrl($scope);
                //数据加载完成之后
                $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
                    //调用示例
                    for(var i=0;i<$scope.demand.designs.length;i++){
                        layer.photos({
                            photos: '#layer-photos-demo'+i
                            ,anim: 0 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                        });
                    }
                    for(var i=0;i<$scope.demand.specimens.length;i++){
                        layer.photos({
                            photos: '#layer-photos-specimen'+i
                            ,anim: 0 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                        });
                    }
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
            });
        }
        //各种基于事件的操作，下面会有进一步介绍
        form.on('submit(basicform)', function(data){
            $scope.demand = data.field;
            $scope.demand.id = data.field.id;
            console.log($scope.demand);
            //angluar 使用其它插件时候 需要在调用插件后使用$scope.$apply()刷新表单绑定数据，测试暂时赋值
            layer.confirm('您是否确定发布需求？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $http({
                    method: 'POST',
                    url: 'api/demands',
                    data:$scope.demand
                }).then(function successCallback(response) {
                    layer.msg('需求发布成功', {
                        icon: 1,
                        time: 1000 //1s后自动关闭
                    },function () {
                        // $state.go("demand");
                        history.back();
                    });
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    layer.msg('需求发布失败，请联系系统管理员', {
                        icon: 2,
                        time: 1000 //1s后自动关闭
                    });
                });
            }, function(){
                layer.msg('已取消', {
                    time: 1000, //1s后自动关闭
                });
            });
            // return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        //多文件列表示例
        var demoListView = $('#demoList')
            ,uploadListIns = upload.render({
            elem: '#testList'
            ,url: '/upload/'
            ,accept: 'file'
            ,multiple: true
            ,auto: false
            ,bindAction: '#testListAction'
            ,choose: function(obj){
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            ,done: function(res, index, upload){
                if(res.code == 0){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            ,error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });
    });

    //审核设计audit
    $scope.auditDesign = function(demand_id,status) {
        if($scope.demand.design_id != null && $scope.demand.design_id!=""){
                //angluar 使用其它插件时候 需要在调用插件后使用$scope.$apply()刷新表单绑定数据，测试暂时赋值
                layer.confirm('您是否确定选中当前产品设计？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    $http({
                        method: 'GET',
                        url: 'api/demands/designAudit',
                        params:{demand_id:demand_id,design_id:$scope.demand.design_id,status:status}
                    }).then(function successCallback(response) {
                        layer.msg('审核成功', {
                            icon: 1,
                            time: 1000 //1s后自动关闭
                        },function () {
                            // $state.go("demand");
                            history.back();
                        });
                    }, function errorCallback(response) {
                        // 请求失败执行代码
                        layer.msg('审核失败，请联系系统管理员', {
                            icon: 2,
                            time: 1000 //1s后自动关闭
                        });
                    });
                }, function(){
                    layer.msg('已取消', {
                        time: 1000, //1s后自动关闭
                    });
                });
        }else{
            layer.msg('请选中产品设计', {
                time: 1000, //1s后自动关闭
            });
        }
    };

    //审核打板audit
    $scope.auditSpecimen = function(demand_id,status) {
        if($scope.demand.specimen_id != null && $scope.demand.specimen_id!=""){
            //angluar 使用其它插件时候 需要在调用插件后使用$scope.$apply()刷新表单绑定数据，测试暂时赋值
            layer.confirm('您是否确定选中当前打板？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $http({
                    method: 'GET',
                    url: 'api/demands/specimenAudit',
                    params:{demand_id:demand_id,specimen_id:$scope.demand.specimen_id,status:status}
                }).then(function successCallback(response) {
                    layer.msg('审核成功', {
                        icon: 1,
                        time: 1000 //1s后自动关闭
                    },function () {
                        // $state.go("demand");
                        history.back();
                    });
                }, function errorCallback(response) {
                    // 请求失败执行代码
                    layer.msg('审核失败，请联系系统管理员', {
                        icon: 2,
                        time: 1000 //1s后自动关闭
                    });
                });
            }, function(){
                layer.msg('已取消', {
                    time: 1000, //1s后自动关闭
                });
            });
        }else{
            layer.msg('请选中打板', {
                time: 1000, //1s后自动关闭
            });
        }
    };
});

mainApp.controller('demandUserController',function ($scope,$http,$stateParams,$state,ngDialog){
    if($stateParams.demand_id != null && $stateParams.demand_id != ""){
        $http({
            method: 'GET',
            url: 'api/demands/petitive/'+$stateParams.demand_id
        }).then(function successCallback(response) {
            if(response.data.length>0){
                var datas = [];
                for(var i = 0; i < response.data.length; i++ ){
                    if (i % 3 == 0) datas.push([]);
                    datas[datas.length-1].push(response.data[i]);
                }
                $scope.datas = datas;
            }else{
                layer.msg('暂无数据', {
                    time: 1000, //2s后自动关闭
                },function () {
                    $state.go("demand");
                });
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
        });
    }

    //选中招标人
    $scope.selectUser = function (demand_id,user_id) {
        layer.confirm('您是确定选中当前设计师？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $http({
                method: 'GET',
                url: 'api/demands/bidWinner',
                params:{demand_id:demand_id,user_id:user_id}
            }).then(function successCallback(response) {
                layer.msg('竞标成功', {
                    icon: 1,
                    time: 1000 //1s后自动关闭
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
                layer.msg('竞标失败', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
            });
        }, function(){
            layer.msg('已取消', {
                time: 1000, //2s后自动关闭
            });
        });
    }
})

/**
 * design manage
 */
mainApp.controller('designController',function ($scope, $http){
    $http({
        method: 'GET',
        url: 'api/demands/findListByDesign'
    }).then(function successCallback(response) {
        $scope.demand = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });
    //完成
    $scope.done = function(demand_id) {
        layer.confirm('您是否确定完成该设计？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $http({
                method: 'GET',
                url: 'api/demands/doneDemandDesign',
                params:{demand_id:demand_id}
            }).then(function successCallback(response) {
                layer.msg('操作成功', {
                    icon: 1,
                    time: 1000 //1s后自动关闭
                },function () {
                    // $state.go("demand");
                    // history.back();
                    location.reload();
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
                layer.msg('操作失败，请联系系统管理员', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
            });
        }, function(){
            layer.msg('已取消', {
                time: 1000, //1s后自动关闭
            });
        });
    };
});

/**
 * specimen manage
 */
mainApp.controller('specimenController',function ($scope, $http){
    $http({
        method: 'GET',
        url: 'api/demands/findListBySpecimen'
    }).then(function successCallback(response) {
        $scope.demand = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    //打板
    $scope.making = function (demand_id) {
        jQuery("#specimen_user_id").val("");//清空值
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['800px', '450px'],
            fixed: false, //不固定
            maxmin: true,
            content: 'specimen-user.html',
            success: function(layero, index){
                // console.log(layero, index);
            },
            end:function(){//执行操作或者关闭iframe
                var specimen_user_id = jQuery("#specimen_user_id").val();
                if(specimen_user_id != ""){
                    $http({
                        method: 'GET',
                        url: 'api/demands/selectSpecimenUser',
                        params:{demand_id:demand_id,specimen_user_id:specimen_user_id}
                    }).then(function successCallback(response) {
                        layer.msg(response.data.msg, {
                            time: 1000, //2s后自动关闭
                        },function () {
                            $state.go("specimen");
                        });
                    }, function errorCallback(response) {
                        // 请求失败执行代码
                    });
                }
            }
        });
    }

    $scope.$on('ngRepeatFinished', function( ngRepeatFinishedEvent ) {
        //数据加载完之后，dataTable渲染
        dataTable();
    });
    //完成
    $scope.done = function(demand_id) {
        layer.confirm('您是否确定完成该设计？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $http({
                method: 'GET',
                url: 'api/demands/doneDemandDesign',
                params:{demand_id:demand_id}
            }).then(function successCallback(response) {
                layer.msg('操作成功', {
                    icon: 1,
                    time: 1000 //1s后自动关闭
                },function () {
                    // $state.go("demand");
                    // history.back();
                    location.reload();
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
                layer.msg('操作失败，请联系系统管理员', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
            });
        }, function(){
            layer.msg('已取消', {
                time: 1000, //1s后自动关闭
            });
        });
    };
    //收样
    $scope.send = function(demand_id) {
        layer.confirm('您是否确定收到样品？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $http({
                method: 'GET',
                url: 'api/demands/doneSendSpecimen',
                params:{demand_id:demand_id}
            }).then(function successCallback(response) {
                layer.msg('操作成功', {
                    icon: 1,
                    time: 1000 //1s后自动关闭
                },function () {
                    // $state.go("demand");
                    // history.back();
                    location.reload();
                });
            }, function errorCallback(response) {
                // 请求失败执行代码
                layer.msg('操作失败，请联系系统管理员', {
                    icon: 2,
                    time: 1000 //1s后自动关闭
                });
            });
        }, function(){
            layer.msg('已取消', {
                time: 1000, //1s后自动关闭
            });
        });
    };
});




//指令
//on-finish-render="ngRepeatFinished"  load js after render datas
mainApp.directive('onFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    }
});

function ctrl($scope){
    if($scope.demand.demand_status == "bid"){//已发布
        $scope.issue = true;
        $scope.bid = true;
    }else if($scope.demand.demand_status == "audit"){//待审核
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
    }else if($scope.demand.demand_status == "reject"){//已驳回
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.reject = true;
    }else if($scope.demand.demand_status == "approve"){//审核通过
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
    }else if($scope.demand.demand_status == "making"){//待打板
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
    }else if($scope.demand.demand_status == "affirm"){//待确认
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
    }else if($scope.demand.demand_status == "send"){//待送样
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
    }else if($scope.demand.demand_status == "specimenAudit"){//样品审核
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
        $scope.specimenAudit = true;
    }else if($scope.demand.demand_status == "specimenReject"){//样品驳回
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
        $scope.specimenAudit = true;
        $scope.specimenReject = true;
    }else if($scope.demand.demand_status == "specimenApprove"){//样品通过
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
        $scope.specimenAudit = true;
        $scope.specimenApprove = true;
    }else if($scope.demand.demand_status == "done"){//已完成
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
        $scope.specimenAudit = true;
        $scope.specimenApprove = true;
        $scope.done = true;
    }else if($scope.demand.demand_status == "using"){//已启用
        $scope.issue = true;
        $scope.bid = true;
        $scope.audits = true;
        $scope.approve = true;
        $scope.dMaking = true;
        $scope.affirm = true;
        $scope.send = true;
        $scope.specimenAudit = true;
        $scope.specimenApprove = true;
        $scope.done = true;
        $scope.dUsing = true;
    }else{
        $scope.issue = true;
    }
}

function dataTable(){
    jQuery('#dyntable2').dataTable({
        "sPaginationType": "full_numbers",
        "aaSortingFixed": [[0,'asc']],
        "fnDrawCallback": function(oSettings) {
            jQuery('input:checkbox,input:radio').uniform();
            //jQuery.uniform.update();
        }
    });
}

//预算和支出
function budgetOutlays() {
    Highcharts.chart('dashboard', {
        chart: {
            polar: true,
            type: 'line'
        },
        title: {
            text: '预算与支出',
            x: -80
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: ['销售', '市场营销', '发展', '客户支持',
                '信息技术', '行政管理'],
            tickmarkPlacement: 'on',
            lineWidth: 0
        },
        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },
        tooltip: {
            shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>${point.y:,.0f}</b><br/>'
        },
        legend: {
            align: 'right',
            verticalAlign: 'top',
            y: 70,
            layout: 'vertical'
        },
        series: [{
            name: '预算拨款',
            data: [43000, 19000, 60000, 35000, 17000, 10000],
            pointPlacement: 'on'
        }, {
            name: '实际支出',
            data: [50000, 39000, 42000, 31000, 26000, 14000],
            pointPlacement: 'on'
        }]
    });
}

//需求分析统计
function demandCount($scope,$http,begin_time,end_time){
    $http({
        method: 'GET',
        url: 'api/index/getCountDemandTable',
        params:{begin_time:begin_time,end_time:end_time}
    }).then(function successCallback(response) {
        $scope.demandCount = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
}

//需求前五
function topFiveDemand($scope,$http) {
    $http({
        method: 'GET',
        url: 'api/index/getTopFiveDemand'
    }).then(function successCallback(response) {
        $scope.topFiveDemand = response.data;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
}

//需求分析图表
function demandAnalyze(begin_time,end_time){
    var text = begin_time +"至"+ end_time;
    if(begin_time == end_time){
        text = begin_time;
    }
    // jQuery.getJSON('/json/index.json',function (data) {
    jQuery.getJSON('/api/index/getStatisticsTable?begin_time='+begin_time+"&end_time="+end_time,function (data) {
        Highcharts.chart('demand', {
            title: {
                text: text+' 需求分析情况'
            },
            subtitle: {
                text: '数据来源: 博策协同设计平台'
            },
            plotOptions: {
                series: {
                    pointStart: Date.UTC(2017, 10, 1), // 开始值
                    // pointEnd: Date.UTC(2017, 10, 1),
                    pointInterval: 24 * 3600 * 1000 // 间隔一天
                }
            },
            xAxis: [{
                type: 'datetime',
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
                // categories: ['一月', '二月', '三月', '四月', '五月', '六月',
                //     '七月', '八月', '九月', '十月', '十一月', '十二月'],
                // crosshair: true,
                // tickPixelInterval : 75,
                // labels: {
                //     formatter: function () {
                //         return Highcharts.dateFormat('%Y/%m/%d', this.value);
                //     }
                // }
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}%',
                    style: {
                        color: Highcharts.getOptions().colors[2]
                    }
                },
                title: {
                    text: '参与度',
                    style: {
                        color: Highcharts.getOptions().colors[2]
                    }
                },
                max:100,
                opposite: true
            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: '需求',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, { // Tertiary yAxis
                gridLineWidth: 0,
                title: {
                    text: '金额',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                labels: {
                    format: '{value} ￥',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true,
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            legend: {
                align: 'left',
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
            },
            series: [{
                name: '金额',
                type: 'spline',
                yAxis: 2,
                data: data.money,
                // data: [490.90, 71.50, 1060.40, 129.20, 144.00, 1761.00, 1354.60, 148.50, 216.40, 194.10, 95.60, 54.40,490.90, 71.50, 1060.40, 129.20, 144.00, 1761.00, 1354.60, 148.50, 216.40, 194.10, 95.60, 54.40,490.90, 71.50, 1060.40, 129.20, 144.00, 1761.00, 1354.60, 148.50, 216.40, 194.10, 95.60, 54.40],
                // pointStart: Date.UTC(2017, 0, 1),
                // pointInterval: 24 * 3600 * 1000 ,// one day
                // // pointStart: Date.UTC(2017, 12, 1,0,0,0),
                // pointEnd:Date.UTC(2017,12,30),
                tooltip: {
                    valueSuffix: ' ￥'
                }
            }, {
                name: '需求',
                type: 'column',
                yAxis: 1,
                data: data.demand,
                // data: [23, 52, 1, 22, 16, 98, 100, 3, 65, 27, 76, 16],
                // pointStart: Date.UTC(2017, 0, 1),
                // pointInterval: 24 * 3600 * 1000, // one day
                // // pointStart: Date.UTC(2017, 12, 1,0,0,0),
                // pointEnd:Date.UTC(2017,12,30),
                marker: {
                    enabled: false
                },
                dashStyle: 'shortdot',
                tooltip: {
                    valueSuffix: ''
                }
            }, {
                name: '参与度',
                type: 'spline',
                data: data.participation,
                // data: [7, 69, 95, 15, 18, 21, 25, 5, 23, 18, 9, 96],
                // pointStart: Date.UTC(2017, 0, 1),
                // pointInterval: 24 * 3600 * 1000, // one day
                // // pointStart: Date.UTC(2017, 12, 1,0,0,0),
                // pointEnd:Date.UTC(2017,12,30),
                tooltip: {
                    valueSuffix: ' %'
                }
            }]
        });
    });
}
//需求占比
function demandProportion() {
    Highcharts.chart('proportion', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '2017-12-04 需求占比'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '需求',
            data: [
                ['系统发布',   100],
                {
                    name: '平台发布',
                    y: 0,
                    sliced: true,
                    selected: true
                },
                ['第三方发布',    0]
            ]
        }]
    });
}
//用户分析
function userAnalyze() {
    Highcharts.chart('userContainer', {
        title: {
            text: '2017-12-04 用户分析情况'
        },
        subtitle: {
            text: '数据来源: 博策协同设计平台'
        },
        xAxis: [{
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            crosshair: true
        }],
        yAxis: [{ // Primary yAxis
            labels: {
                format: '{value}',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            title: {
                text: '需求',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            }
        }, { // Secondary yAxis
            title: {
                text: '金额',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            labels: {
                format: '{value} ￥',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
        legend: {
            align: 'left',
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
        },
        series: [{
            name: '需求',
            type: 'column',
            data: [3, 20, 4, 5, 9, 6, 1, 0, 20, 60, 23, 31],
            tooltip: {
                valueSuffix: ' '
            }
        }, {
            name: '金额',
            type: 'spline',
            yAxis: 1,
            data: [1524.00, 85424.90, 35424.50, 5112.50, 8754.20, 365.50, 8457.20, 2202.50, 3545.30, 4574.30, 2442.90, 0.00],
            tooltip: {
                valueSuffix: '￥'
            }
        }]
    });
}


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
