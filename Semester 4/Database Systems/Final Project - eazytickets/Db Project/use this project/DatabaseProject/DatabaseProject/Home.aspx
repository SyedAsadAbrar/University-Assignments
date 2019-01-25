<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="DatabaseProject.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
<style>
    #HomeLink
    {
        background-color:#45a29e !important;
    }
    .contain {
        display: inline-block;
        float: none;
    }
    .row-fluid {
        overflow: auto;
        white-space: nowrap;
    }
    .image{
        width:300px !important;
        height:500px !important;
        
    }
</style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div id="demo" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="carousel-image" src="images/movie_infinity-war.jpg" alt="Infinity War" ID="img1">
      <div class="carousel-caption">
        <h3>Avengers:Infinity War</h3>  
      </div>
    </div>
    <div class="carousel-item">
      <img class="carousel-image" src="images/movie_rampage.jpg" alt="Rampage">
      <div class="carousel-caption">
        <h3>Rampage</h3>  
      </div>  
    </div>
    <div class="carousel-item">
      <img class="carousel-image" src="images/concert_alizafar.jpg" alt="Ali Zafar Concert">
      <div class="carousel-caption">
        <h3>Ali Zafar Concert</h3>  
      </div>
    </div>
  </div>
  

      <!-- Left and right controls -->
              <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
              </a>
              <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
              </a>
            </div>


    <asp:Repeater ID="UpcomingEvents" runat="server">
        <HeaderTemplate>
            <div class="row-fluid">
        </HeaderTemplate>
        <ItemTemplate>
                  <div class="contain " >
                 <img class="image" src="<%# Eval("ImageLink") %>"  alt="<%# Eval("name") %>" ID="img1">
                 <h3 class="caption"><%# Eval("name") %></h3>
                  <h5 class="caption"><%# Eval("comingdate") %></h5>
                 </div>
         </ItemTemplate>
        <FooterTemplate>
           </div>
        </FooterTemplate>
    </asp:Repeater>



 
   
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="NotificationsPlaceholder" runat="server">
    
</asp:Content>
