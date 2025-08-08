<%@page import="model.Vendeur"%>
<%@page import="model.Client"%>
<%@page import="model.Mois"%>
<%@page import="model.ProduitDetaille"%>
<%@page import="model.MatierPremier"%>
<%@page import="model.Produit"%>
<%@page import="java.util.List"%>
<%
    List<Produit> les_produit = (List<Produit>)request.getAttribute("les_produit");
    List<MatierPremier> les_matier_premier = (List<MatierPremier>)request.getAttribute("les_matiere_premier");
    List<ProduitDetaille> les_produit_detaile = (List<ProduitDetaille>)request.getAttribute("les_produit_detaite");
    String erreur = (String)request.getAttribute("erreur");
    List<Mois> les_mois = (List<Mois>)request.getAttribute("les_mois");
    List<Client> les_client = (List<Client>)request.getAttribute("les_client");
    List<Vendeur> les_vendeur = (List<Vendeur>)request.getAttribute("les_vendeur");
    
%>

<script>
        // Transmettre l'erreur depuis le serveur � JavaScript
        const erreur = "<%= erreur != null ? erreur.replaceAll("\"", "\\\"") : "" %>";
        
        // V�rifier et afficher l'erreur dans une alerte
        if (erreur) {
            alert(erreur);
        }
    </script>

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
      <h1>Form Elements</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item">Forms</li>
          <li class="breadcrumb-item active">Elements</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Formulaire produit Detaille</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulaireconstructioncontroleur" method="POST">
                        
                        
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Produit</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="produitId" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_produit!= null){
                                        for(Produit une_produit : les_produit){ %>
                                            <option value="<%= une_produit.getId() %>"><%= une_produit.getNom() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>
                        
                                    
                        
                        <div class="row mb-3">
                            <label for="adresse" class="col-sm-2 col-form-label">Prix</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="adresse" name="prix" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Date fabrication</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" id="contact" name="date_fabrication" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Date expiration</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" id="contact" name="date_expiration" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="dettaill_produit" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>
                    <!-- Fin Formulaire Laboratoire -->
            </div>
          </div>

        </div>


        <div class="col-lg-6">

            <div class="card">
              <div class="card-body">
  
  
                <h5 class="card-title">Formulaire ConstructProduit</h5>

                    <form action="<%= request.getContextPath() %>/Formulaireconstructioncontroleur" method="post">
                        <!-- Champ Quantité -->
                        <div class="row mb-3">
                            <label for="inputQuantiter" class="col-sm-2 col-form-label">Quantité</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="inputQuantiter" name="quantiter" step="0.01" required>
                            </div>
                        </div>

                        <!-- Sélecteur Produit Détaille -->
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Produit Détaille</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="produitDetailleId" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_produit_detaile!= null){
                                        for(ProduitDetaille une_produit : les_produit_detaile){ %>
                                            <option value="<%= une_produit.getId() %>"><%= une_produit.getProduit().getNom() %> date Fabrication <%= une_produit.getDateFabrication() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>

                        <!-- Sélecteur Matière Première -->
                        <div class="row mb-3">
                            <label for="inputMatierPremier" class="col-sm-2 col-form-label">Matière Première</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputMatierPremier" name="matierPremierId" required>
                                    <!-- Options des matières premières seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_matier_premier!= null){
                                        for(MatierPremier une_produit : les_matier_premier){ %>
                                            <option value="<%= une_produit.getId() %>"><%= une_produit.getNom() %></option>
                                          <% }
                                        }
                                    %>
                                    
                                    
                                    <!-- Vous pouvez générer les options dynamiquement avec PHP -->
                                </select>
                            </div>
                        </div>

                        <!-- Bouton Soumettre -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="construction" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>


              </div>
            </div>
  
          </div>
                                    
                                    
         
                                    
         <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Formulaire Vente</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulaireconstructioncontroleur" method="POST">
                        
                        
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Vendeur</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="vendeurid" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_vendeur!= null){
                                        for(Vendeur une_produit_detaile : les_vendeur){ %>
                                            <option value="<%= une_produit_detaile.getId() %>"><%= une_produit_detaile.getNom() %> <%= une_produit_detaile.getPrenom() %> </option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>
                                  
                                    
                        
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Produit detailler</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="produitdettaileId" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_produit_detaile!= null){
                                        for(ProduitDetaille une_produit_detaile : les_produit_detaile){ %>
                                            <option value="<%= une_produit_detaile.getId() %>"><%= une_produit_detaile.getProduit().getNom() %> prix : <%= une_produit_detaile.getPrix() %> , <%= une_produit_detaile.getDateFabrication() %> - <%= une_produit_detaile.getDateExpiration() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>
                                    
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Date vente</label>
                            <div class="col-sm-10">
                                <input 
                                    type="date" 
                                    class="form-control" 
                                    id="contact" 
                                    name="date_vente" 
                                    onchange="validateDate()"
                                >
                                <div id="dateError" style="color: red; display: none;">La date ne peut pas �tre dans le futur.</div>
                            </div>
                        </div>
                        
                        
                        <script>
                            function validateDate() {
                                const dateInput = document.getElementById("contact");
                                const errorDiv = document.getElementById("dateError");
                                const selectedDate = new Date(dateInput.value); // R�cup�re la date saisie
                                const today = new Date(); // R�cup�re la date actuelle

                                // Supprime l'heure de la date actuelle pour une comparaison stricte
                                today.setHours(0, 0, 0, 0);

                                // Calcul de la date d'apr�s-demain
                                const dayAfterTomorrow = new Date(today);
                                dayAfterTomorrow.setDate(today.getDate() + 2);

                                // V�rifie si la date saisie est aujourd'hui, demain ou plus tard
                                if (selectedDate >= dayAfterTomorrow) {
                                    // Si la date est aujourd'hui, demain ou une date future, affiche un message d'erreur
                                    errorDiv.style.display = "block";
                                    dateInput.value = ""; // R�initialise le champ de saisie
                                } else {
                                    // Si la date est valide (avant apr�s-demain), masque le message d'erreur
                                    errorDiv.style.display = "none";
                                }
                            }
                        </script>

                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Prix</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="contact" name="prix" required>
                            </div>
                        </div>
                                    
                                    
                       <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Client</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="client_id" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                    <%
                                        if(les_client!= null){
                                        for(Client une_client : les_client){ %>
                                            <option value="<%= une_client.getId() %>"><%= une_client.getNom() %>  <%= une_client.getPrenom() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="vente" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>
                    <!-- Fin Formulaire Laboratoire -->
            </div>
          </div>

        </div>
                                    
                                    
                                    
        
       <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Formulaire Conseile</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulaireconstructioncontroleur" method="POST">
                        
                        
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Produit</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="produitid" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                     <%
                                        if(les_produit!= null){
                                        for(Produit une_produit : les_produit){ %>
                                            <option value="<%= une_produit.getId() %>"><%= une_produit.getNom() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="inputProduitDetaille" class="col-sm-2 col-form-label">Mois</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="inputProduitDetaille" name="Moisid" required>
                                    <!-- Options des produits seront chargées dynamiquement ici -->
                                    
                                     <%
                                        if(les_mois!= null){
                                        for(Mois une_mois : les_mois){ %>
                                            <option value="<%= une_mois.getId() %>"><%= une_mois.getNom() %></option>
                                          <% }
                                        }
                                    %>
                                 
                                </select>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Anner</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="contact" name="Anner" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="conseile" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>
                    <!-- Fin Formulaire Laboratoire -->s
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