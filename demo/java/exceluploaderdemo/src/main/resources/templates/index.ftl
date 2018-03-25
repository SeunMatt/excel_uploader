<!DOCTYPE html>
<html lang="en">
<head>
    <title>Excel Uploader Demo</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap4.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>

<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar" style="background-color:#563D7C;">

    <a class="navbar-brand mr-0 mr-md-2" href="/" aria-label="Bootstrap">
        <span class="fa fa-github fa-2x"></span>
    </a>

    <div class="navbar-nav-scroll">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
                <a class="nav-link " href="https://github.com/SeunMatt/excel_uploader">Star on Github</a>
            </li>
        </ul>
    </div>

    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item nav-link">
            Built with <span style="color: #ff000085;" class="fa fa-heart"></span>  by <a class="" target="_blank" href="https://www.linkedin.com/in/seun-matt-06351955">Seun Matt</a>
        </li>
    </ul>
</header>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6 mx-sm-auto">
            <h1 class="display-4">Bulk upload Demo</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <p>Kindly select an excel file with at least 3 columns and map the files to any of the available 3 column names</p>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-header">
                    <h3 class="">Import Data</h3>
                </div>
                <!-- /.box-header -->
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <!--This is the import type selector-->
                            <div class="form-group">
                                <select class="form-control" id="dataType">
                                    <option value="-1" disabled selected>Select Data to Import</option>
                                    <option selected value="/process">Import Posts Data</option>
                                </select>
                            </div>
                            <!-- This is the file chooser input field-->
                            <div class="form-group">
                                <input type="file" id="fileUploader" class="btn btn-fill btn-primary btn-large" />
                            </div>
                        </div>
                    </div>
                    <!-- This is the Blank output/progress div-->
                    <div id="tableOutput">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/http_ajax.googleapis.com_ajax_libs_jquery_3.2.1_jquery.min.js"></script>
<script src="/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.12.6_umd_popper.js"></script>
<script src="/js/http_maxcdn.bootstrapcdn.com_bootstrap_4.0.0-beta.2_js_bootstrap.js"></script>
<script src="/js/http_unpkg.com_sweetalert_dist_sweetalert.min.js"></script>
<script src="/js/http_rawgit.com_eligrey_FileSaver.js_master_FileSaver.js"></script>
<script src="/js/http_unpkg.com_xlsx_dist_xlsx.full.min.js"></script>
<script src="/js/excel_uploader.js"></script>

<script>
    $(document).ready( function () {
        new ExcelUploader({
            maxInAGroup: 1000,
            serverColumnNames: ["Name", "Email", "Phone Number"],
            importTypeSelector: "#dataType",
            fileChooserSelector: "#fileUploader",
            outputSelector: "#tableOutput",
            extraData: {_token: "23333323323223323232"}
        });
    });
</script>
</body>
</html>