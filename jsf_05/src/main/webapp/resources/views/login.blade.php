@extends('layouts.main')

@section('title')
<h1> Strona logowania <br /> </h1>
@endsection

@section('content')
<form action="{{ route('login-submit') }}" method="post"  class="pure-form pure-form-aligned bottom-margin">
    @csrf
	<legend>Logowanie do systemu</legend>
	<fieldset>
        <div class="pure-control-group">
			<label for="id_login">Login: </label>
                        <input id="id_login" type="text" name="login" placeholder="Wpisz login lub email" />
		</div>
        <div class="pure-control-group">
			<label for="id_password">Password: </label>
			<input id="id_password" type="password" name="password" placeholder="Wpisz hasło"/><br />
		</div>
		<div class="pure-controls" style="display: flex; gap: 10px;" >
			<input type="submit" value="zaloguj" class="pure-button pure-button-primary"/>
                        
                       <button type="button" onclick="window.location.href='{{ route('registration') }}'" class="pure-button pure-button-primary">ZAREJESTRUJ SIĘ</button>
                </div>
        
	</fieldset>
</form>	

@endsection
