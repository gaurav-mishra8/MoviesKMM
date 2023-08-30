//
//  MovieItem.swift
//  iosApp
//
//  Created by Gaurav Mishra on 29/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieItem: View {
    var movie : Movie
    
    var body: some View {
        VStack(alignment: .leading){
            Text(movie.title)
                .font(.body)
                .fontWeight(.medium)
                .padding(.bottom, 3)
            
            Spacer()
            
            Text(movie.overview)
                .font(.body)
                .fontWeight(.medium)
                .padding(.bottom, 3)
        }
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
