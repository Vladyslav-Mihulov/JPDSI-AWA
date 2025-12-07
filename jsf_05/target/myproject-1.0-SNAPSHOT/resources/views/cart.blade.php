@extends('layouts.main')

@section('title')
<h1>Twój koszyk</h1>
@endsection

@section('content')
<section id="cart" class="wrapper">
    <div class="inner">
        <header class="major">
            <h2>Zawartość koszyka</h2>
        </header>

        @if(count($items) > 0)
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Produkt</th>
                            <th>Ilość</th>
                            <th>Cena za szt.</th>
                            <th>Razem</th>
                        </tr>
                    </thead>
                    <tbody>
                        @php
                            $total = 0;
                        @endphp
                        @foreach($items as $item)
                            @php
                                $unitPrice = $item->quantity > 0 ? $item->price / $item->quantity : 0;
                                $sum = $item->price;
                                $total += $sum;
                            @endphp
                            <tr>
                                <td>{{ $item->product->name_product }}</td>
                                <td>
                                    {{ $item->quantity }}

                                    <form style="display:inline" method="POST" action="{{ route('cart-delete') }}">
                                        @csrf
                                        <input type="hidden" name="order_id_order" value="{{ $item->order_id_order }}">
                                        <input type="hidden" name="product_id_product" value="{{ $item->product_id_product }}">
                                        <button type="submit" class="button small">-</button>
                                    </form>
                                </td>
                                <td>{{ number_format($unitPrice, 2) }} zł</td>
                                <td>{{ number_format($sum, 2) }} zł</td>
                            </tr>
                        @endforeach
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="3" style="text-align: right;">Suma całkowita:</th>
                            <th>{{ number_format($total, 2) }} zł</th>
                        </tr>
                    </tfoot>
                </table>
            </div>

            <ul class="actions">
                <form method="POST" action="{{ route('cart-submit') }}">
                    @csrf
                    <button type="submit" class="button primary">Złóż zamówienie</button>
                </form>
            </ul>
        @else
            <p>Koszyk jest pusty. Dodaj produkty, aby je zobaczyć tutaj.</p>
        @endif
    </div>
</section>
@endsection
