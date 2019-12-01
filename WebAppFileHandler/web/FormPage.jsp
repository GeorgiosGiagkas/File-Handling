<%-- 
    Document   : FormPage
    Created on : Sep 18, 2019, 11:04:13 AM
    Author     : giagkas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--google font family-->
        <link href="https://fonts.googleapis.com/css?family=Chilanka&display=swap" rel="stylesheet">
        <!--awsome fonts-->
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <!--bootstrap-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!--custom css-->
        <link rel="stylesheet" href="/WebAppFileHandler/CSS/style.css">
        <title>Form Page</title>
    </head>
    <body>
        <div class="container">
            <h3>File-Transfer Mini App</h3>

            <div id="uploading-buffer">                
                <div  class="spinner-border mx-auto mb-3" role="status"></div>
                <p >Uploading...</p>
            </div>


            <!--upload file form-->
            <form id="file-form" action="Uploader" method="post" enctype="multipart/form-data">
                <input  id="input-file" type="file" name="file">
                <input type="submit" value="Upload">
            </form>
            
            <!--table-->
            <div>
                <c:if test="${!files.isEmpty()}">
                    <table>
                        <thead>
                            <tr style="background: brown; color:white">
                                <th>#ID</th>
                                <th>File Name</th>
                                <th>Delete</th>
                                <th>Download</th>
                            </tr>  
                        </thead>
                        <tbody>
                            <c:forEach items="${files}" var="file">
                                <tr>
                                    <td>
                                        <c:out value="${file.fileId}" />

                                    </td>
                                    <td>
                                        <c:out value="${file.fileName}"/>
                                    </td>
                                    <td>
                                        <a href="#" class="file-delete" data-toggle="modal" data-target="#delete-file"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                    </td>
                                    <td>
                                        <a href="Downloader?file-id=${file.fileId}" download><i class="fa fa-download" aria-hidden="true"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>   

                </c:if>
                <c:if test="${files.isEmpty()}">
                    <h4>No file info to display. Please upload a file!</h4>
                </c:if>
            </div>


            <!--modal pop up-->
            <div class="modal fade" id="delete-file">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div style="background:brown;" class="modal-header">
                            <h5 style="color:white;">Caution</h5>
                            <a class="close" data-dismiss="modal">&times;</a>
                        </div>
                        <div class="modal-body"></div>
                        <form id="delete-form" method="post">
                            <p></p>
                            <button class="btn"  type="submit">Delete</button>                            
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <!--bootstap scripts-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="/WebAppFileHandler/JS/app.js" ></script>

    </body>
</html>
