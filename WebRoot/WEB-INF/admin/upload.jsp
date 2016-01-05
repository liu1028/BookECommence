<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path=request.getContextPath();
%>

<div>
    <form id="formcon">
	<ul style="margin:20px auto;width:820px;">
        <li>        
	        <fieldset><legend>上传共享图片</legend>
	            <input type="file" name="uploadImage" id="uploadImage"  />
	         </fieldset>            
        </li>
        <li id="previewLi"  style="display:none;text-align:center;">
            <fieldset><legend>预览上传图片</legend>
                <img id="uploadPreview" src="" width="620" height="380" style="border-radius:8px;"/> 
            </fieldset>
        </li>
        <li style="text-align:center;padding-top:15px;">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:270px;" onclick="formSubmit()" size="large">上传图片</a> 
        </li>
	</ul>
	</form>
</div>
<script src="<%=path%>/js/ViewPic.js"></script>
<script src="<%=path%>/js/jquery.ajaxform.js"></script>

<script>
	function formSubmit() {
	    if ($('#uploadImage').val() == "") {
	        $.messager.alert('提示', '上传图片不能为空');
	        return;
	    }
	    
	    var str = /.jpg|.jpeg|.gif|.png/;
	    if (!str.test($('#uploadImage').val().toLowerCase())) {
	        $.messager.alert('提示', '上传文件格式不正确，请重新选择才能上传');
	        return;
	    }
	
	    var option = {
	        type: 'post',
	        url: '<%=path%>/servlet/admin/upload',
	        success: function (result) {
	            var json = $.parseJSON(result);
	            if (json.status) 
	                $.messager.alert('提示', '提交成功');
	            else
	                $.messager.alert('提示', '系统忙，请稍后再试！');	            	
	            
                document.getElementById("uploadPreview").src="";
                $('#uploadImage').val('');
                $('#previewLi').hide();

	        }
	    };
	
	   $('#formcon').ajaxSubmit(option);	   
	}
</script>