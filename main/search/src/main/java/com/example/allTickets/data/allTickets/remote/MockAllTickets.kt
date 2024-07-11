package com.example.allTickets.data.allTickets.remote

import com.example.allTickets.data.allTickets.AllTicketsRemoteSource
import com.example.allTickets.data.allTickets.model.ArrivalDto
import com.example.allTickets.data.allTickets.model.DepartureDto
import com.example.allTickets.data.allTickets.model.HandLuggageDto
import com.example.allTickets.data.allTickets.model.LuggageDto
import com.example.allTickets.data.allTickets.model.PriceDto
import com.example.allTickets.data.allTickets.model.TicketDto
import javax.inject.Inject

class MockAllTickets
    @Inject
    constructor() : AllTicketsRemoteSource {
        override suspend fun getAllTickets(): List<TicketDto> =
            listOf(
                TicketDto(
                    id = 100,
                    badge = "Самый удобный",
                    price = PriceDto(value = 1999),
                    providerName = "На сайте Купибилет",
                    company = "Якутия",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T03:15:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T07:10:00",
                            airport = "AER",
                        ),
                    hasTransfer = false,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 1082),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "55x20x40",
                        ),
                    isReturnable = false,
                    isExchangable = true,
                ),
                TicketDto(
                    id = 101,
                    price = PriceDto(value = 9999),
                    providerName = "На сайте Победа",
                    company = "Победа",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T04:00:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T08:30:00",
                            airport = "AER",
                        ),
                    hasTransfer = false,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 1602),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "36x30x27",
                        ),
                    isReturnable = false,
                    isExchangable = true,
                ),
                TicketDto(
                    id = 102,
                    price = PriceDto(value = 8500),
                    providerName = "На сайте Turkish Airlines",
                    company = "Турецкие авиалинии",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T15:00:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T18:40:00",
                            airport = "AER",
                        ),
                    hasTransfer = false,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = true,
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "55x20x40",
                        ),
                    isReturnable = false,
                    isExchangable = false,
                ),
                TicketDto(
                    id = 103,
                    badge = "Рекомендуемый",
                    price = PriceDto(value = 8086),
                    providerName = "На сайте Уральские авиалинии",
                    company = "Уральские авиалинии",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T11:30:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T15:00:00",
                            airport = "AER",
                        ),
                    hasTransfer = false,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 1570),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "55x20x40",
                        ),
                    isReturnable = true,
                    isExchangable = true,
                ),
                TicketDto(
                    id = 104,
                    price = PriceDto(value = 11560),
                    providerName = "На сайте Aeroflot",
                    company = "Аэрофлот",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T11:50:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T15:20:00",
                            airport = "AER",
                        ),
                    hasTransfer = true,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 999),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "55x20x40",
                        ),
                    isReturnable = false,
                    isExchangable = true,
                ),
                TicketDto(
                    id = 105,
                    price = PriceDto(value = 15600),
                    providerName = "На сайте Aeroflot",
                    company = "Аэрофлот",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T13:50:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-23T17:20:00",
                            airport = "AER",
                        ),
                    hasTransfer = true,
                    hasVisaTransfer = true,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 1999),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = true,
                            size = "55x20x40",
                        ),
                    isReturnable = true,
                    isExchangable = true,
                ),
                TicketDto(
                    id = 106,
                    price = PriceDto(value = 9500),
                    providerName = "На сайте Aeroflot",
                    company = "Аэрофлот",
                    departure =
                        DepartureDto(
                            town = "Москва",
                            date = "2024-02-23T21:00:00",
                            airport = "VKO",
                        ),
                    arrival =
                        ArrivalDto(
                            town = "Сочи",
                            date = "2024-02-24T00:20:00",
                            airport = "AER",
                        ),
                    hasTransfer = false,
                    hasVisaTransfer = false,
                    luggage =
                        LuggageDto(
                            hasLuggage = false,
                            price = PriceDto(value = 5999),
                        ),
                    handLuggage =
                        HandLuggageDto(
                            hasHandLuggage = false,
                        ),
                    isReturnable = false,
                    isExchangable = false,
                ),
            )
    }
