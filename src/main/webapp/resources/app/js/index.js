$(function() {
    App.init();

    var Index = (function() {
        var me = {};

        // 处理一级菜单点击
        me.handleMenuClick = function() {
            //每一个ul下的li的点击事件
            $('#page-sidebar-menu > li').click(function(e) {
                //获取到ul
                var menu = $('#page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');

                // 添加选中 打开的样式
                // $(this).addClass('active');
            });
        };

        // 处理子菜单点击
        me.handleSubMenuClick = function() {
            //每一个li下的a标签的点击事件
            $('#page-sidebar-menu li a').click(function(e) {
                e.preventDefault();
                var url = this.href;
                if (url != null && url != 'javascript:;') {
                    $.get(url, function(data) {
                        $('#main-content').html(data);
                    });
                }
            });
        };
        //自定义方法
        me.init = function() {
            me.handleMenuClick();
            me.handleSubMenuClick();
        };
        return me;
    })();

    Index.init();
    //触发首页的事件
    $('#btn-dashboard').trigger("click");
});