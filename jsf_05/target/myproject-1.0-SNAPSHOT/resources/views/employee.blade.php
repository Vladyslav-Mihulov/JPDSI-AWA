@extends('layouts.main')

@section('title')
    <h1>Strona dla pracownika</h1>
@endsection

@section('content')
    <h2>Lista zamówień</h2>

    <table border="1" cellpadding="5" cellspacing="0" style="width: 100%; border-collapse: collapse;">
        <thead>
            <tr>
                <th>ID zamówienia</th>
                <th>Status</th>
                <th>Data złożenia</th>
                <th>Data zakończenia</th>
                <th>Łączna cena</th>
                <th>Produkty</th>
                <th>Akcje</th> 
            </tr>
        </thead>
        <tbody>
            @foreach ($orders as $order)
                <tr>
                    <td>{{ $order->id_order }}</td>
                    <td>
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
                    </td>
                    <td>{{ $order->date_order->format('d.m.Y H:i') }}</td>
                    <td>
                        @if($order->date_end_order)
                            {{ $order->date_end_order->format('d.m.Y H:i') }}
                        @else
                            nie zakonczone
                        @endif
                    </td>
                    <td>{{ number_format($order->total_price, 2) }} zł</td>
                    <td>
                        @foreach ($order->productOrders as $productOrder)
                            <strong>{{ $productOrder->product->name_product ?? 'Produkt usunięty' }}</strong><br>
                            Ilość: {{ $productOrder->quantity }}<br>
                            Cena jednostkowa: {{ number_format($productOrder->price, 2) }} zł<br><br>
                        @endforeach
                    </td>
                    <td>
                        @if($order->status < 3)
                        <form action="{{ route('employee-submit') }}" method="POST">
                            @csrf
                            <input type="hidden" name="id" value="{{ $order->id_order }}">
                            <button type="submit">Zmień status</button>
                        </form>
                        @endif
                    </td>
                </tr>
            @endforeach
        </tbody>
    </table>
@endsection
