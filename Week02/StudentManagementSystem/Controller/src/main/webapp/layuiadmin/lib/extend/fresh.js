layui.use('jquery',function () {

    $ = layui.jquery;
    $("body").bind("keydown",function(event){
        if (event.keyCode == 116) {
            event.preventDefault(); //阻止默认刷新
            // location.reload();
            //采用location.reload()在火狐下可能会有问题，火狐会保留上一次链接
            location=location;
        }
    });
});
