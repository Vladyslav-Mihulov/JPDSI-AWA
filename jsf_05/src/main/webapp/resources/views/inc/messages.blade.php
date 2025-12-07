@if ($errors->any())
    <div class="alert alert-danger" style="border: 2px solid red; color: black; border-radius: 10px; padding: 15px; background-color: rgba(255, 0, 0, 0.1);">
        <ul>
            @foreach($errors->all() as $error)
                <li>{{ $error }}</li>
            @endforeach
        </ul>
    </div>
@endif

@if(session('success'))
    <div class="alert alert-success" style="border: 2px solid green; color: black; border-radius: 10px; padding: 15px; background-color: rgba(0, 255, 0, 0.1);">
        {{ session('success') }}
    </div>
@endif
