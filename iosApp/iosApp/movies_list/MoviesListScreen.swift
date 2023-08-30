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
    private var moviesRepository : MoviesRepositoryImpl
    @StateObject var viewModel = MoviesListViewModel(moviesRepository: nil)
    
    init(moviesRepository : MoviesRepositoryImpl) {
        self.moviesRepository = moviesRepository
        
    }
    
    var body: some View {
        VStack {
            List {
                ForEach(viewModel.movies, id: \.self.id) {movie in
                    MovieItem(movie: movie)
                }
            }
            .listStyle(.plain)
            
        }.onAppear {
            viewModel.setRepo(moviesRepository:moviesRepository)
            viewModel.loadMovies()
        }
    }
}

struct MoviesListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
