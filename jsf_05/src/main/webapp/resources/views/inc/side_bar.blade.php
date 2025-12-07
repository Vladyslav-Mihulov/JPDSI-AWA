<div id="sidebar">
    <div class="inner">

        <!-- Menu -->
        <nav id="menu">
            <header class="major">
                <h2>Menu</h2>
            </header>
            <ul>
                <li><a href="{{ route('home') }}">Homepage</a></li>
                <li><a href="{{ route('profile') }}">Profile</a></li>
                <li><a href="{{ route('login') }}">login</a></li>
                <li><a href="{{ route('orders') }}">Historia zamówień</a></li>

                @php $user = auth()->user(); @endphp

                @if($user && $user->hasRole('admin'))
                    <li><a href="{{ route('admin') }}">Dla admina</a></li>
                @endif

                @if($user && $user->hasRole('employee'))
                    <li>
                        <span class="opener">Dla pracownika</span>
                        <ul>
                            <li><a href="{{ route('employee') }}">Wszystkie zamówienia</a></li>
                            <li><a href="{{ route('employee-ord') }}">Zamówienia</a></li>
                        </ul>
                    </li>
                @endif

            </ul>

            <!-- Section -->
            <section style="margin-top: 30px;">
                <header class="major">
                    <h2>Skontaktuj się z nami</h2>
                </header>
                        
                <ul class="contact">
                    <li class="icon solid fa-envelope"><a href="#">..@..</a></li>
                    <li class="icon solid fa-phone">+00-000-000-000</li>
                    <li class="icon solid fa-home">....<br />...</li>
                </ul>
            </section>
        </nav>

        <!-- Footer -->
        <footer id="footer">
            <p class="copyright">&copy; Author: Vladyslav Mihulov </p>
        </footer>

    </div>
</div>
