<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
<section class="vh-100" style="background-color: #2f2f2f;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-12 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">
                                <form action="/main" method="post">
                                    <input name="action" value="addTour" type="hidden">

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                        <span class="h1 fw-bold mb-0">Add a Tour</span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="numberTicket"  type="number"
                                               required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label">Number ticket</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="date" type="date" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label">Date</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="description" maxlength="63"
                                               type="text" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label">Description</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="urlImage" type="text" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label">UrlImage</label>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input name="namePlace" type="text" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label">Name place</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

</html>
