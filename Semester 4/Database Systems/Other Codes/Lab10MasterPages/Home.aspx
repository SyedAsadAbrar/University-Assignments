﻿<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
  
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <h2>Welcome to Home Page</h2>
    This is our home page. Please enter your information in the following form. 
    Name:
    <br /><asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
    <br />
    Student ID:
    <br /><asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
    <br />
    <asp:Button ID="Button1" runat="server" Text="Save"></asp:Button>
    <br />
</asp:Content>