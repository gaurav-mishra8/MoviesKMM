//
//  MoviesListScreen.swift
//  iosApp
//
//  Created by Gaurav Mishra on 29/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MoviesListScreen: View {
    @StateObject private var viewModel: MoviesListViewModel
    
    init(moviesRepository: MoviesRepositoryImpl) {
        self._viewModel = StateObject(wrappedValue: MoviesListViewModel(moviesRepository: moviesRepository))
    }
    
    var body: some View {
        VStack() {
            List(viewModel.movies, id: \.id) { movie in
                MovieItem(movie: movie)
            }
            .listStyle(.plain)
        }
        .navigationTitle("Trending Movies")
        .onAppear {
            viewModel.loadMovies()
        }
    }
}
