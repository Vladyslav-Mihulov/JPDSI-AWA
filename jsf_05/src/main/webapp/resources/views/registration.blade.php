@extends('layouts.main')

@section('title')
<h1> Strona logowania <br /> </h1>
@endsection

@section('content')
<form action="{{ route('registration-submit') }}" method="post"  class="pure-form pure-form-aligned bottom-margin">
    @csrf
    
	<legend>Rejestracja do systemu</legend>
	<fieldset>
        <div class="pure-control-group">
			<label for="id_login">Login: </label>
			<input id="id_login" type="text" name="login" placeholder="Wpisz login" />
		</div>
            
        <div class="pure-control-group">
			<label for="id_email">Email: </label>
			<input id="id_email" type="text" name="email" placeholder="Wpisz email"/>
                </div>
            
        <div class="pure-control-group">
			<label for="id_fname">Imie: </label>
			<input id="id_fname" type="text" name="fname" placeholder="Wpisz imie"/>
                </div>
            
        <div class="pure-control-group">
			<label for="id_fname">Nazwisko: </label>
			<input id="id_lname" type="text" name="lname" placeholder="Wpisz nazwisko"/>
                </div>
            
        <div class="pure-control-group">
			<label for="id_password1">Password: </label>
			<input id="id_password1" type="password" name="password1" placeholder="Wpisz hasło" />
		</div>
            
        <div class="pure-control-group">
			<label for="id_password2">Powtórz password: </label>
			<input id="id_password2" type="password" name="password2" placeholder="Wpisz powtórz hasło" /><br />
		</div>
		<div class="pure-controls" " >
			<input type="submit" value="Rejestruj" class="pure-button pure-button-primary"/>
                        
                     </div>
        
	</fieldset>
</form>	

@endsection
