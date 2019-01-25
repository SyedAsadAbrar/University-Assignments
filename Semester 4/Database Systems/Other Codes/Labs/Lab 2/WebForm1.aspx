<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication3.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
    <style>
        body{
            font-family:@Malgun Gothic;
            font-size: 2em;
            color: Blue
        }
        h1{
            font-family:@Microsoft JhengHei;
            color:green
        }
        .buttonCSS {
            height:50px;
            color:blue;
            border:3px solid;
            border-color:blue;
        }
        #submit{
            height:30px;
            color:blue;
            border:3px solid;
            border-color:blue;
        }
    </style>
<body>
    <form id="form1" runat="server">
        <div>
            THIS IS THE HOME PAGE

            <h1>This is Heading 1</h1>
            <h1>This is also Heading 1</h1>
            <asp:Button ID="Button1" CssClass="buttonCSS" runat="server" Text="Button 1" />
            <br />
            <br />
            <asp:Button ID="Button2" CssClass="buttonCSS" runat="server" Text="Button 2" />
            <br />
            <br />
            <asp:Button ID="submit" runat="server" Text="Submit" />
            <br />
            <br />
        </div>
    </form>
</body>
</html>
