<%@page language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        function  upload() {
            //js file 对象
            var file=$("#file")[0].files[0];

            //js form
            var form=new FormData();
            form.append("file",file);

            //jquery ajax
            var opt={
                url:"/upload/do_upload",
                type:"post",
                contentType:false,
                processData:false,
                data:form,
                success:function(data){
                    var json=eval("("+data+")");
                    $("#img").attr("src","/upload/"+json.url);
                }
            };
            $.ajax(opt);
        }

    </script>
</head>
<body>
<img id="img" src="" width="200" height="200"/>
<input type="file" name="file" id="file"/>
<input type="button" value="上传" onclick="upload()"/>
</body>
</html>
