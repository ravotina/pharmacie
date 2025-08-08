
<%@page import="model.Vente"%>
<%@page import="model.TypeProduit"%>
<%@page import="model.Querison"%>
<%@page import="model.ProduitDetaille"%>
<%@page import="java.util.List"%>
<% 
List<Querison> les_querison = (List<Querison>)request.getAttribute("les_querison");
%>



<%@page import="model.Produit"%>
<%@page import="model.Maladie"%>
<%@page import="model.Age"%>
<%@page import="model.Laboratoir"%>
<%@page import="model.Uniter"%>
<% 
   /** List<Uniter> les_uniter = (List<Uniter>)request.getAttribute("les_uniter");
    List<Laboratoir> les_laboratoir = (List<Laboratoir>)request.getAttribute("les_laboratoir");
    List<Age> les_age = (List<Age>)request.getAttribute("les_age");
    List<Maladie> les_maladie = (List<Maladie>)request.getAttribute("les_maladie");
    List<Produit> les_produit = (List<Produit>)request.getAttribute("les_produit");
    List<TypeProduit> les_type_produit = (List<TypeProduit>)request.getAttribute("les_type_produit");
    List<Vente> les_vente = (List<Vente>)request.getAttribute("les_vente");*/
   List<ProduitDetaille> les_produit_detailles = (List<ProduitDetaille>)request.getAttribute("les_produit_detailles");
  List<Produit> les_produit = (List<Produit>)request.getAttribute("les_produit");
    
    

%>



<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Dashboard - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Updated: Apr 20 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <%@ include file="logo.jsp" %>




    <div class="search-bar">
      <form class="search-form d-flex align-items-center" method="POST" action="#">
        <input type="text" name="query" placeholder="Search" title="Enter search keyword">
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item dropdown pe-3">
          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
            <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6>Kevin Anderson</h6>
              <span>Web Designer</span>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                <i class="bi bi-person"></i>
                <span>My Profile</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                <i class="bi bi-gear"></i>
                <span>Account Settings</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
                <i class="bi bi-question-circle"></i>
                <span>Need Help?</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="#">
                <i class="bi bi-box-arrow-right"></i>
                <span>Sign Out</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->




      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->


  <%@ include file="Sidebar.jsp" %>
  

  

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Data Tables</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active">Data</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
  
    <section class="section">
      <div class="row">
        <div class="col-lg-12">
  
          <div class="card">
            <div class="card-body">

  
              <h5 class="card-title">Historique produits</h5>
              
              
              <!-- Formulaire pour Laboratoire -->
                    
                                
               
                 <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/ListeProduitDetailleControleur" method="GET">
                               
                         <div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Produit</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="idproduit" >
                                    <option value="">Produit</option>
                                    <!-- Options de laboratoires Ã  remplir dynamiquement -->

                                    <% 
                                        if(les_produit!=null){
                                            for(Produit une_produit : les_produit){ %>
                                            <option value="<%= une_produit.getId() %>"><%= une_produit.getNom() %> </option>
                                        <% }
                                        }
                                    %>
                                </select>
                            </div>
                            </div>
                        
                        
                        
                        
                        <script>
                            function validateDate() {
                                const dateInput = document.getElementById("contact");
                                const errorDiv = document.getElementById("dateError");
                                const selectedDate = new Date(dateInput.value); // Récupère la date saisie
                                const today = new Date(); // Récupère la date actuelle

                                // Supprime l'heure de la date actuelle pour une comparaison stricte
                                today.setHours(0, 0, 0, 0);

                                // Calcul de la date d'après-demain
                                const dayAfterTomorrow = new Date(today);
                                dayAfterTomorrow.setDate(today.getDate() + 2);

                                // Vérifie si la date saisie est aujourd'hui, demain ou plus tard
                                if (selectedDate >= dayAfterTomorrow) {
                                    // Si la date est aujourd'hui, demain ou une date future, affiche un message d'erreur
                                    errorDiv.style.display = "block";
                                    dateInput.value = ""; // Réinitialise le champ de saisie
                                } else {
                                    // Si la date est valide (avant après-demain), masque le message d'erreur
                                    errorDiv.style.display = "none";
                                }
                            }
                        </script>
                                
                                
                         <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="recherche2" class="btn btn-primary">recherche</button>
                            </div>
                        </div>
              
                    </form>
               
              
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th>Nom produit</th>
                    <th>Date</th>
                    <th>Date d'expirarion</th>
                    <th> prix</th>
                  </tr>
                </thead>
                <tbody>
                    
                 <%
                     if(les_produit_detailles!=null){ 
                        for(ProduitDetaille une_produit_detaille : les_produit_detailles){%>
                        <tr>
                        <td><%= une_produit_detaille.getProduit().getNom() %></td>
                        <td><%= une_produit_detaille.getDateFabrication() %></td>
                        <td><%= une_produit_detaille.getDateExpiration() %></td>
                        <td> <%= une_produit_detaille.getPrix() %> </td>
                      </tr>
                    <% } } else { %>
                    <p> aucune donner </p>
                    <% }%>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->
  
            </div>
          </div>
  
        </div>
      </div>
    </section>
  
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>

