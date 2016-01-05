

function loginspan_click(obj) {
    $(obj).css('border-bottom', '3px solid red');
    $('#register_span').css('border-bottom', 'none');
    $('.register_part').hide();
    $('.login_part').show();

    $('.login_part input[type="text"],.login_part input[type="password"]').val('');
    $('.login_part span').hide();
}
function registerspan_click(obj) {
    $(obj).css('border-bottom', '3px solid red');
    $('#login_span').css('border-bottom', 'none');
    $('.register_part').show();
    $('.login_part').hide();

    $('.register_part input[type="text"],.register_part input[type="password"]').val('');
    $('.register_part span').hide();
}

function user_empty(obj) {
    if ($(obj).val() == '')
        $(obj).siblings('span').eq(0).show();
    else
        $(obj).siblings('span').eq(0).hide();
}
function password_empty(obj) {
    if ($(obj).val() == '')
        $(obj).siblings('span').show();
    else
        $(obj).siblings('span').hide();
}


function changeCode() {
    var ran = Math.random() * 100;
    $('#vcode_img').attr("src", "pages/ValidateCode.aspx?cmd="+ran);
}
function login_validate() {
    var username=$('#login_u').val();
    var password= $('#login_p').val();
    var vcode=$('#login_v').val();
    if (username== "" || password == "" || vcode == "") {
        layer.alert('请将要求填写的地方填写完毕，谢谢！', { icon: 6 });
        return false;
    }

    $.ajax({
        type: 'post',
        url: 'handler/login.ashx',
        data: { username: username, password: password, vcode: vcode },
        success: function (result) {
            var jd = $.parseJSON(result);
            if (jd.type == "1") {
                layer.alert('用户名或密码错误！', { icon: 5 });
                changeCode();
                return;
            } else if (jd.type == "2") {
                layer.alert('验证码错误！', { icon: 5 });
                changeCode();
                return;
            } else if (jd.type == "3") {
                window.location.href = "pages/system/default.aspx";
            } else if (jd.type == "4")
                window.location.href = "index.aspx";
        }
    });

    return false;
}

function register_validate() {

}

function IsUsernameExits() {

}
