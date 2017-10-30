$(function () {
    App.init();

    var Index = (function () {
        var me = {};

        // 处理一级菜单点击
        me.handleMenuClick = function () {
            //每一个ul下的li的点击事件
            $('#page-sidebar-menu > li').click(function (e) {
                //获取到ul
                var menu = $('#page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');

                // 添加选中 打开的样式
                // $(this).addClass('active');
            });
        };

        // 处理子菜单点击
        me.handleSubMenuClick = function () {
            //每一个li下的a标签的点击事件
            $('#page-sidebar-menu li a').click(function (e) {
                e.preventDefault();
                var url = this.href;
                if (url != null && url != 'javascript:;') {

                    /**iframe方法**/
                $("#mainContext").attr('src',url);

                    /*   $.get(url, function (data) {
                         /!*动态加载js到父页面的HEAD中去
                         var headTag = document.getElementsByTagName("HEAD").item(0);
                           var s1 = document.createElement("script");
                           s1.src = "resources/js/login/supersized.3.2.7.min.js";
                           s1.type = "text/javascript";
                           s1.charset = "utf-8";
                           headTag.appendChild(s1);
                           var s2 = document.createElement("script");
                           s2.src = "resources/js/login/supersized-init.js";
                           s2.type = "text/javascript";
                           s2.charset = "utf-8";
                           headTag.appendChild(s2);
                           var s3 = document.createElement("script");
                           s3.src = "resources/js/login/scripts.js";
                           s3.type = "text/javascript";
                           s3.charset = "utf-8";
                           headTag.appendChild(s3);*!/
                           $('#main-content').html(data);
                       });*/
               /* $.ajax({
                        type : "get",
                        url : url,
                        async : false,
                        success : function(data){
                          //  data = eval("(" + data + ")");
                           // aDataSet = data;
                            $('#main-content').html(data);
                        }
                    });*/
                }
            });
        };
        //自定义方法
        me.init = function () {
            me.handleMenuClick();
            me.handleSubMenuClick();
        };
        return me;
    })();
    //js获取菜单
    $.get("http://localhost:8081/menus/getMenus", function (menuResult) {
        //动态添加菜单
        $(".page-sidebar-menu").append(menuResult);
        //先獲取菜單才能給dom元素綁定事件
        Index.init();
        //触发首页的事件
        $('#btn-dashboard').trigger("click");

    });
});