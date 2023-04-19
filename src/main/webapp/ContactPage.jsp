<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Contact page</title>
        <!-- cnd stylesheets -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
        />
        <script src="https://kit.fontawesome.com/1374dbcea1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="Test.css" />
    </head>
    <body onload="read()">
        <header>
            <div class="Header">
				<div class="ProAdd">
					<div class="Profile">
						<i class="fa-solid fa-circle-user"></i>
					</div>
					
				</div>
				<div class="Searchbar">
					<input id="search" onkeyup="searchContact()" class="Searchbox" placeholder="Search" type="text"></input>
					<div class="SearchIcon">
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
				</div>
			</div>
            <button
                type="button"
                class="btn btn-success"
                id="export"
                onclick="saveFile()"
            ><i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
            <span>Export</span>
            </button>
        </header>
        <!-- content  start -->
        <div class="container-fluid">
            <div id="root">
                
            </div>
            <a href="#New" id="Add" class="btn btn-primary" onclick="AddRow()">
                <i class="fa-solid fa-user-plus" aria-hidden="true"></i>
                    <!-- <i class="fa fa-plus" aria-hidden="true"></i> -->
                    <!-- <i class="fa-solid fa-user-plus"></i> -->
            </a>
        </div>
        <!-- content end -->
        <!-- cdn links -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js"></script>
        <script src="https://cdn.jsdelivr.net/g/filesaver.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://kit.fontawesome.com/de2421ab51.js"
            crossorigin="anonymous"
        ></script>
        <!-- MyScripts -->
        <script src="Contact.js"></script>
    </body>
</html>
