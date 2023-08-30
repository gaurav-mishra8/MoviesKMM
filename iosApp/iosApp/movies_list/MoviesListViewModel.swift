//
//  MoviesListViewModel.swift
//  iosApp
//
//  Created by Gaurav Mishra on 29/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension MoviesListScreen {
    @MainActor class MoviesListViewModel : ObservableObject {
        
        private var moviesRepository : MoviesRepositoryImpl? = nil
        
        @Published private(set) var movies = [Movie]()

        init(moviesRepository : MoviesRepositoryImpl? = nil) {
            self.moviesRepository = moviesRepository
        }
        
        func loadMovies() {
            print("loading movies start")
            moviesRepository?.getPopularMovies(forceLoad: false, completionHandler: { movies, error in
                print("fetched movies")
                DispatchQueue.main.async {
                    self.movies = movies ?? []
                }
            })
            print("loading movies end")
        }
        
        func setRepo(moviesRepository : MoviesRepositoryImpl) {
            self.moviesRepository = moviesRepository
        }
    }
}
