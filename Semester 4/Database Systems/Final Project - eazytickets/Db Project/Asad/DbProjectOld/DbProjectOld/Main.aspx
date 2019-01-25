<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="DbProjectOld.Main" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        #HomeLink {
            background-color: #45a29e !important;
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
                <img class="carousel-image" src="images/movie_infinity-war.jpg" alt="Infinity War">
                <div class="carousel-caption">
                    <h3>Upcoming Events</h3>
                </div>
            </div>
            <div class="carousel-item">
                <img class="carousel-image" src="images/movie_rampage.jpg" alt="Rampage">
                <div class="carousel-caption">
                    <h3>Upcoming Events</h3>
                </div>
            </div>
            <div class="carousel-item">
                <img class="carousel-image" src="images/concert_alizafar.jpg" alt="Ali Zafar Concert">
                <div class="carousel-caption">
                    <h3>Upcoming Events</h3>
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

</asp:Content>

