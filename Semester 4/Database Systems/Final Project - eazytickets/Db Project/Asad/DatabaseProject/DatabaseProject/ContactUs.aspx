<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="ContactUs.aspx.cs" Inherits="DatabaseProject.ContactUs" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ShoppingCartPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="NotificationsPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <h1>Contact Us</h1>
    <p>Email</p>

    <asp:TextBox ID="Email" CssClass=" form-control" Placeholder="Enter your Email Adress here" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>

    <br />
    <p>Message</p>

    <asp:TextBox ID="Comment" CssClass=" form-control" Rows="5" Placeholder="Enter your message here" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>

    <br />
    <asp:Button ID="MessageSend" CssClass="btn btn-warning btn-block btn-lg" runat="server" Text="Send Message" OnClick=" MessageSend_Click" />
    <br />
    <asp:Label ID="Message" runat="server" Text=""></asp:Label>
</asp:Content>
