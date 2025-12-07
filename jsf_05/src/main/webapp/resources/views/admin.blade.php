@extends('layouts.main')

@section('title')
    <h1>Strona dla admina</h1>
@endsection

@section('content')

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<h2>Lista użytkowników</h2>

<!-- Search -->
<section id="search" class="alt" style="margin-bottom: 20px;">
    <input 
        type="text" 
        name="search" 
        id="query" 
        placeholder="Search" 
        value="{{ old('search', $search ?? '') }}" 
    />
</section>

<div id="table-wrapper">
    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Email</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Role</th>
                <th>Dodany przez</th>
                <th>Zmodyfikowany przez</th>
                <th>Utworzono</th>
                <th>Zmodyfikowano</th>
            </tr>
        </thead>
        <tbody id="user-table">
            @foreach ($users as $user)
                <tr>
                    <td>{{ $user->id_user }}</td>
                    <td>{{ $user->login }}</td>
                    <td>{{ $user->email }}</td>
                    <td>{{ $user->first_name }}</td>
                    <td>{{ $user->last_name }}</td>
                    <td>
                        @foreach($user->roles as $role)
                            <span>{{ $role->role_name }}</span><br />
                        @endforeach
                    </td>
                    <td>{{ $user->who_created }}</td>
                    <td>{{ $user->who_modify }}</td>
                    <td>{{ $user->created_at->format('Y-m-d H:i') }}</td>
                    <td>{{ $user->updated_at->format('Y-m-d H:i') }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>

    <div id="pagination-links" style="margin-top: 20px;">
        {{ $users->appends(['search' => $search])->links('pagination::bootstrap-5') }}
    </div>
</div>

<script>

function fetchUsers(url) {
    fetch(url, {
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => response.text())
    .then(html => {
        let parser = new DOMParser();
        let doc = parser.parseFromString(html, 'text/html');


        let newTbody = doc.getElementById('user-table');
        if(newTbody) {
            document.getElementById('user-table').innerHTML = newTbody.innerHTML;
        }


        let newPagination = doc.getElementById('pagination-links');
        if(newPagination) {
            document.getElementById('pagination-links').innerHTML = newPagination.innerHTML;
        }
    })
    .catch(e => console.error(e));
}


document.getElementById('query').addEventListener('input', function () {
    let query = this.value.trim();
    let url = "{{ route('admin') }}" + "?search=" + encodeURIComponent(query);
    fetchUsers(url);
});


document.addEventListener('click', function(e) {
    if(e.target.closest('#pagination-links a')) {
        e.preventDefault();
        let url = e.target.closest('a').href;
        fetchUsers(url);
    }
});
</script>

@endsection
