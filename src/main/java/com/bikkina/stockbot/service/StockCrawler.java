package com.bikkina.stockbot.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class StockCrawler {

    public static void main(String[] args) throws IOException {
        String[] symbols = new String[]{"ACN"};
        Map<String, Stock> stocks = new HashMap<>();

        {
            stocks = YahooFinance.get(symbols);
        }

        Stock intel = stocks.get("ACN");
        List<HistoricalQuote> historicalQuotes = intel.getHistory();
        System.out.format("%13s%16s%16s%18s\n", "DATE", "LOW", "HIGH", "VOLUME");
        System.out.format("%36s\n", "===================================================================");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for (HistoricalQuote historicalQuote : historicalQuotes) {
            if (nonNull(historicalQuote.getLow())) {
                System.out.format("%16s%16s%16s%16s\n", df.format(historicalQuote.getDate().getTime()), historicalQuote.getLow(), historicalQuote.getHigh(), historicalQuote.getVolume());

            }
        }

    }

}
