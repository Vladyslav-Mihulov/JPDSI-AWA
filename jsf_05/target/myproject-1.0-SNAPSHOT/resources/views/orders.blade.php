@extends('layouts.main')

@section('title')
    <h1>Twoje zamówienia</h1>
@endsection

@section('content')
<section class="wrapper">
    <div class="inner">

        @if($ord->isEmpty())
            <p>Nie masz jeszcze żadnych zamówień.</p>
        @else
            @foreach($ord as $order)
                <h2>Zamówienie #{{ $order->id_order }}</h2>
                <p>Data zamówienia: {{ $order->date_order->format('d.m.Y H:i') }}</p>
                
                @if($order->date_end_order)
                    <p>Data zakończenia: {{ $order->date_end_order->format('d.m.Y H:i') }}</p>
                @else
                    <p>Data zakończenia: nie zakonczone</p>
                @endif
                
                <p>Status: 
                    @switch($order->status)
                        @case(0)
                            Koszyk
                            @break
                        @case(1)
                            Złożone
                            @break
                        @case(2)
                            Potwierdzone
                            @break
                        @case(3)
                            Wykonane
                            @break
                        @default
                            Nieznany
                    @endswitch
                </p>

                <table>
                    <thead>
                        <tr>
                            <th>Produkt</th>
                            <th>Ilość</th>
                            <th>Cena za sztukę</th>
                            <th>Cena za pozycję</th>
                        </tr>
                    </thead>
                    <tbody>
                        @foreach($order->productOrders as $item)
                            <tr>
                                <td>{{ $item->product->name_product }}</td>
                                <td>{{ $item->quantity }}</td>
                                <td>{{ number_format($item->product->price, 2) }} zł</td> 
                                <td>{{ number_format($item->price, 2) }} zł</td> 
                            </tr>
                        @endforeach
                    </tbody>
                </table>

                <p><strong>Łączna cena zamówienia:</strong> {{ number_format($order->total_price, 2) }} zł</p>
                <hr>
            @endforeach
        @endif
    </div>
</section>
@endsection
