import SwiftUI
import shared

@main
struct iOSApp: App {
    
    let repo = MoviesRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory())
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                MoviesListScreen(moviesRepository: repo)
            }.accentColor(.black)
		}
	}
}
