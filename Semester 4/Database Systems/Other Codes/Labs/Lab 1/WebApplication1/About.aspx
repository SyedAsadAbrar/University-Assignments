<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="WebApplication1.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Image ID="Image1" ImageUrl="http://osnas.org/athletics_camps/basketball/boys_varsity/files/large_17605.png" runat="server" />
        <br /><br />
        <b><asp:Label ID="Label1" runat="server" Text="Create An Account"></asp:Label></b>
        <br /><br />
        <asp:Label ID="Label2" runat="server" Text="First Name:"></asp:Label>
        <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
        <asp:Label ID="Label3" runat="server" Text="Last Name:"></asp:Label>
        <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
        <br /><br />
        <asp:Label ID="Label4" runat="server" Text="Username:"></asp:Label>
        <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox>
        <br /><br />
        <asp:Label ID="Label5" runat="server" Text="Password:"></asp:Label>
        <asp:TextBox ID="TextBox4" runat="server"></asp:TextBox>
        <br /><br />
        <asp:Label ID="Label6" runat="server" Text="Date of Birth:"></asp:Label>
        <asp:Calendar ID="Calendar1" runat="server"></asp:Calendar>
        <br /><br />
        <asp:Label ID="Label7" runat="server" Text="Country:"></asp:Label>
        <asp:DropDownList ID="DropDownList1" runat="server">
            <asp:ListItem Text="Select" Value="0"></asp:ListItem>
            <asp:ListItem Text="Pakistan" Value="1"></asp:ListItem>
            <asp:ListItem Text="United States of America" Value="2"></asp:ListItem>
            <asp:ListItem Text="India" Value="3"></asp:ListItem>
            <asp:ListItem Text="China" Value="4"></asp:ListItem>
            <asp:ListItem Text="Afghanistan" Value="5"></asp:ListItem>
        </asp:DropDownList>
        <br /><br />
        <asp:Label ID="Label8" runat="server" Text="Gender:"></asp:Label>
        <asp:RadioButtonList ID="RadioButtonList1" runat="server">
            <asp:ListItem Text="Male" Value="0"></asp:ListItem>
            <asp:ListItem Text="Female" Value="1"></asp:ListItem>
        </asp:RadioButtonList>
        <br /><br />
        <asp:CheckBox ID="CheckBox1" Text="Enable Privacy" runat="server" />
        <br /><br />
        <asp:Button ID="Button1" runat="server" Text="Create Account" />
    </div>
    </form>
</body>
</html>
