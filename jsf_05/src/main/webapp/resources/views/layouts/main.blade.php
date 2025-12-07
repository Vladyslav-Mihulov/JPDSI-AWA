<!doctype html>
<html lang="pl">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/project/assets/css/main.css" />	
</head>

<body>

<!-- Wrapper -->
    <div id="wrapper">

		<!-- Main -->
		<div id="main">
                    <div class="inner">
			<!-- Header -->
			@include('inc.header')
                       
			<!-- Banner -->
			<section id="banner">
				<div class="content">
					<header>
					<h1>@yield('title')</h1>
                                        </header>
					
                                        <p>
                                        @yield('content')
                                        </p>
                                        
                                        @include('inc.messages')
                                        
				</div>	
			</section>				
                    </div>

		</div>
                
                <!-- side bar-->
		@include('inc.side_bar')


    </div>

	<!-- Scripts -->
	<script src="/project/assets/js/jquery.min.js"></script>
	<script src="/project/assets/js/browser.min.js"></script>
	<script src="/project/assets/js/breakpoints.min.js"></script>
	<script src="/project/assets/js/util.js"></script>
	<script src="/project/assets/js/main.js"></script>

</body>
</html>