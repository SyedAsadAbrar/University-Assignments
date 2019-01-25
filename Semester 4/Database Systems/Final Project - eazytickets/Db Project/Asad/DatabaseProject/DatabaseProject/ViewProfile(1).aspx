<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="ViewProfile.aspx.cs" Inherits="DatabaseProject.ViewProfile" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
       .top-panel
       {
           border-bottom: 1px solid whitesmoke !important;
           padding-top:100px;
           padding-bottom:50px;
           padding-left:100px;
       }
       .id{
           font-size:25px !important;
           font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
       }
       .info,p{
            font-size:15px !important;
           font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
       }
    </style>

</asp:Content>


<asp:Content ID="Content4" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="top-panel">
        <span class="fa fa-user fa-5x"> <asp:Label CssClass="id" ID="UserId" runat="server"></asp:Label> </span>
       
    </div>
      <table class="table table-borderless">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
        <th>City</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><asp:Label CssClass="info" ID="Fname" runat="server"></asp:Label></td>
        <td><asp:Label CssClass="info" ID="Lname" runat="server"></asp:Label></td>
        <td><asp:Label CssClass="info" ID="Phone" runat="server"></asp:Label></td>
        <td><asp:Label CssClass="info" ID="City" runat="server"></asp:Label></td>
      </tr>
    </tbody>
  </table>
</asp:Content>
