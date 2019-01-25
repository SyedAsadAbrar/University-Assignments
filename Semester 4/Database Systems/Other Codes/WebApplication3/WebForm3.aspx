<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm3.aspx.cs" Inherits="WebApplication3.WebForm3" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
    <script type="text/javascript">
        function ConvertfromPKR() /*Convert to other currency from PKR*/ {
            var opt = document.getElementById('Currency').value;
            var pkrstr = document.getElementById('TextBox1').value;
            var out = parseInt(opt) * parseInt(pkrstr);
        }
    </script>
<body>
    <form id="form1" runat="server">
        <div>
        </div>
    </form>
</body>
</html>
