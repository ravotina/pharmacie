
<%@page import="model.Sexe"%>
<%@page import="model.TypeProduit"%>
<%@page import="model.Produit"%>
<%@page import="model.Maladie"%>
<%@page import="model.Age"%>
<%@page import="model.Laboratoir"%>
<%@page import="model.Uniter"%>
<%@page import="java.util.List"%>
<% 
    List<Uniter> les_uniter = (List<Uniter>)request.getAttribute("les_uniter");
    List<Laboratoir> les_laboratoir = (List<Laboratoir>)request.getAttribute("les_laboratoir");
    List<Age> les_age = (List<Age>)request.getAttribute("les_age");
    List<Maladie> les_maladie = (List<Maladie>)request.getAttribute("les_maladie");
    List<Produit> les_produit = (List<Produit>)request.getAttribute("les_produit");
    List<TypeProduit> les_type_produit = (List<TypeProduit>)request.getAttribute("les_type_produit"); 
    List<Sexe> les_type_sexe = (List<Sexe>)request.getAttribute("les_sexe"); 
    
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
                <h5 class="card-title">Formulaire Laboratoire</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                        <div class="row mb-3">
                            <label for="nom" class="col-sm-2 col-form-label">Nom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="nom" name="nom" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="adresse" class="col-sm-2 col-form-label">Adresse</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="adresse" name="adresse" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Contact</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="contact" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="laboratoire" class="btn btn-primary">Soumettre</button>
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


              <h5 class="card-title">Formulaire Produit</h5>

                <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="post">
                    <div class="row mb-3">
                        <label for="inputNom" class="col-sm-2 col-form-label">Nom du Produit</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNom" name="nom" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputDescription" name="description" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="selectLaboratoir" class="col-sm-2 col-form-label">Laboratoire</label>
                        <div class="col-sm-10">
                            <select class="form-select" id="selectLaboratoir" name="laboratoirId" required>
                                <option value="">Sélectionner un laboratoire</option>
                                <!-- Options de laboratoires à remplir dynamiquement -->
                                
                                <% 
                                    if(les_laboratoir!=null){
                                        for(Laboratoir une_laboratoir : les_laboratoir){ %>
                                        <option value="<%= une_laboratoir.getId() %>"><%= une_laboratoir.getNom() %></option>
                                    <% }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                            
                    <div class="row mb-3">
                        <label for="selectLaboratoir" class="col-sm-2 col-form-label">Age</label>
                        <div class="col-sm-10">
                            <select class="form-select" id="selectLaboratoir" name="ageId" required>
                                <option value="">Age</option>
                                <!-- Options de laboratoires à remplir dynamiquement -->
                                
                                <% 
                                    if(les_age!=null){
                                        for(Age une_age : les_age){ %>
                                        <option value="<%= une_age.getId() %>"><%= une_age.getAgeDebut() %> - <%= une_age.getAgeFin() %>  <%= une_age.getDescription() %> </option>
                                    <% }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                            
                            
                    <div class="row mb-3">
                        <label for="selectLaboratoir" class="col-sm-2 col-form-label">Type Produit</label>
                        <div class="col-sm-10">
                            <select class="form-select" id="selectLaboratoir" name="produit_id" required>
                                <option value="">Type Produit</option>
                                <!-- Options de laboratoires à remplir dynamiquement -->
                                
                                <% 
                                    if(les_type_produit!=null){
                                        for( TypeProduit protype: les_type_produit){ %>
                                        <option value="<%= protype.getId() %>"><%= protype.getNom() %> </option>
                                    <% }
                                    }
                                %>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm-10 offset-sm-2">
                            <button type="submit" name="signef" value="produit" class="btn btn-primary">Soumettre</button>
                        </div>
                    </div>
                </form>
            </div>
          </div>

        </div>


        <div class="col-lg-6">

            <div class="card">
              <div class="card-body">
  
  
                <h5 class="card-title">Formulaire Matière Première</h5>

                <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="post">
                    <div class="row mb-3">
                        <label for="inputNom" class="col-sm-2 col-form-label">Nom</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNom" name="nom" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="inputPrix" class="col-sm-2 col-form-label">Prix</label>
                        <div class="col-sm-10">
                            <input type="number" step="0.01" class="form-control" id="inputPrix" name="prix" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="selectUniter" class="col-sm-2 col-form-label">Unité</label>
                        <div class="col-sm-10">
                            <select class="form-select" id="selectUniter" name="uniterId" required>
                                <option value="">Sélectionner une unité</option>
                                
                                <% 
                                    if(les_uniter!=null){
                                        for(Uniter une_uniter : les_uniter){ %>
                                        <option value="<%= une_uniter.getId() %>"><%= une_uniter.getIniter() %></option>
                                    <% }
                                    }
                                %>
       
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm-10 offset-sm-2">
                            <button type="submit" name="signef" value="matier_premier" class="btn btn-primary">Soumettre</button>
                        </div>
                    </div>
                </form>

              </div>
            </div>
  
          </div>




        

          <div class="col-lg-6">

            <div class="card">
              <div class="card-body">
  
  
                <h5 class="card-title">Formulaire Maladie</h5>

                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="post">
                        <div class="row mb-3">
                            <label for="inputNom" class="col-sm-2 col-form-label">Nom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNom" name="nom" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="inputDescription" name="description" rows="3" required></textarea>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="maladie" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>


              </div>
            </div>
  
          </div>
                        
                        
                        
                        
          <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Formulaire Age</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                        <div class="row mb-3">
                            <label for="nom" class="col-sm-2 col-form-label">Age debu</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="nom" name="ager_debu" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="adresse" class="col-sm-2 col-form-label">Age fin</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="adresse" name="age_fin" required>
                            </div>
                        </div>
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">description</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="description" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="age" class="btn btn-primary">Soumettre</button>
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
                <h5 class="card-title">Formulaire guerison</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                            <div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Maladie</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="idmaladie" required>
                                    <option value="">Maladie</option>
                                    <!-- Options de laboratoires à remplir dynamiquement -->

                                    <% 
                                        if(les_maladie!=null){
                                            for(Maladie une_maladie : les_maladie){ %>
                                            <option value="<%= une_maladie.getId() %>"><%= une_maladie.getNom() %> </option>
                                        <% }
                                        }
                                    %>
                                </select>
                            </div>
                            </div>
                                
                                
                           <div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Produit</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="idproduit" required>
                                    <option value="">Produit</option>
                                    <!-- Options de laboratoires à remplir dynamiquement -->

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
                
                        <div class="row mb-3">
                            <label for="adresse" class="col-sm-2 col-form-label">Pourcentage</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="adresse" name="pourcentage" required>
                            </div>
                        </div>
                                
                                
                         <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="gerison" class="btn btn-primary">Soumettre</button>
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
                <h5 class="card-title">Type produit</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Nom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="nom" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="typeproduit" class="btn btn-primary">Soumettre</button>
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
                <h5 class="card-title">Client</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Nom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="nom" required>
                            </div>
                        </div>
                        
                         <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Prenom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="prenom" required>
                            </div>
                        </div>

                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="client" class="btn btn-primary">Soumettre</button>
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
                <h5 class="card-title">Vendeur</h5>

                    <!-- Formulaire pour Laboratoire -->
                    <form action="<%= request.getContextPath() %>/Formulairecontroleur" method="POST">
                        
                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Nom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="nom" required>
                            </div>
                        </div>
                        
                         <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Prenom</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="prenom" required>
                            </div>
                        </div>


                       

                        <div class="row mb-3">
                            <label for="contact" class="col-sm-2 col-form-label">Prenom</label>
                            <div class="col-sm-10">
                                <%
                                    if(les_type_sexe != null){
                                    for(Sexe une_sexe : les_type_sexe ){ %>
                                            <p> <%= une_sexe.getNom() %>   <input type="radio"  name="sexe" value="<%= une_sexe.getId() %>" id=""> </p>
                                    <% }
                                    }
                                 %>
                             
                            
                            </div>
                        </div>




                        <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="vendeur" class="btn btn-primary">Soumettre</button>
                            </div>
                        </div>
                    </form>
                    <!-- Fin Formulaire Laboratoire -->


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