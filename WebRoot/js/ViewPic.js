$('#uploadImage').bind('change',viewPic);

function viewPic() {
    var str = /.jpg|.jpeg|.gif|.png/;
    if (!str.test($('#uploadImage').val().toLowerCase())) {
        $.messager.show({
            title: '注意',
            msg:'<b style="font-size:16px;">您上传的文件后缀名格式不正确!</b>',
            showType:'slide',
            timeout:3000,
            height:300
        });
        return;
    }
    $("#previewLi").fadeOut();

    var oFReader = new FileReader();
    oFReader.onload = function (e) {
        document.getElementById("uploadPreview").src = e.target.result;
    }
    if (document.getElementById("uploadImage").files.length === 0) {
        return;
    }
    var oFile = document.getElementById("uploadImage").files[0];
    oFReader.readAsDataURL(oFile);

    $("#previewLi").fadeIn();
} 
