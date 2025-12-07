@extends('layouts.main')

@section('title')
<h1>Homepage</h1>
@endsection

@section('content')

<section>
    <header class="major">
        <h2>Menu</h2>
    </header>
    <div class="posts">
        @foreach ($products as $product)
        <article>
            <a class="image">
                <img src="{{ asset('images/' . $product->image) }}" alt="{{ $product->name_product }}" />
            </a>
            <h3>{{ $product->name_product }}</h3>
            <p>{{ $product->description }}</p>
            <p>Cena: {{ number_format($product->price, 2) }} zł</p>
            <form action="{{ route('home-submit') }}" method="POST">
                @csrf
                <input type="hidden" name="product_id" value="{{ $product->id_product }}">
                <button type="submit" class="button">Kup</button>
            </form>
        </article>
        @endforeach
    </div>
</section>

<a href="{{ route('cart') }}" class="cart">
    🛒 Koszyk
</a>
<style>
.cart {
    position: fixed;
    right: 20px;
    bottom: 20px;
    background: #f5f5f5;
    padding: 12px 18px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
    cursor: pointer;
    z-index: 9999;
    font-weight: bold;
    font-size: 16px;
    user-select: none;
    transition: background 0.3s;
}

.cart:hover {
    background: #e0e0e0;
}
</style>

@endsection
