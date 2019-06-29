/**
 * 菜单
 * */
//获取路径uri
var pathUri=window.location.href;

$(function(){
    layui.use(['element', 'layer'], function(){
        var element = layui.element, layer = layui.layer;
        // 左侧导航区域（可配合layui已有的垂直导航）
        if(menuList != null){
            getMenus(menuList);
            element.render('nav');
        }else{
            layer.alert("权限不足，请联系管理员",function () {
                //退出
                window.location.href="logout.action";
            });
        }
    });
})
// 初始化左侧菜单导航
var getMenus=function(menuList){
    //回显选中
    var ul=$("<ul class='layui-nav layui-nav-tree' lay-filter='test'></ul>");
    for(var i=0;i < menuList.length;i++){
        var node = menuList[i];
        // 菜单权限
        if(node.itemType == 0){
            if(node.parentId == 0){
                var li=$("<li class='layui-nav-item' flag='"+node.id+"'></li>");
                // 父级无page
                var a=$("<a class='' href='javascript:;'><i class='"+node.icon+"'></i>&nbsp;&nbsp;"+node.permissionName+"</a>");
                li.append(a);
                //获取子节点
                var childArry = getParentArry(node.id, menuList);
                console.log(childArry);
                if(childArry.length>0){
                    a.append("<span class='layui-nav-more'></span>");
                    var dl=$("<dl class='layui-nav-child'></dl>");
                    for (var y in childArry) {
                        var dd=$("<dd><a href='"+ contextPath + childArry[y].permissionUrl +"'><i class='"+childArry[y].icon+"'></i>&nbsp;&nbsp;" +childArry[y].permissionName+"</a></dd>");
                        //判断选中状态
                        if(pathUri.indexOf(childArry[y].permissionUrl) > 0){
                            li.addClass("layui-nav-itemed");
                            dd.addClass("layui-this")
                        }
                        //TODO 由于layui菜单不是规范统一的，多级菜单需要手动更改样式实现；
                        dl.append(dd);
                    }
                    li.append(dl);
                }
                ul.append(li);
            }
        }
    }
    $(".layui-side-scroll").append(ul);
}

//根据菜单主键id获取下级菜单
//id：菜单主键id
//arry：菜单数组信息
function getParentArry(id, arry) {
    var newArry = new Array();
    for (var x in arry) {
        if (arry[x].parentId == id)
            newArry.push(arry[x]);
    }
    return newArry;
}
