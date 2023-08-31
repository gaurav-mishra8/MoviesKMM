//
//  MovieItem.swift
//  iosApp
//
//  Created by Gaurav Mishra on 29/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct MovieItem: View {
    var movie: Movie

    var body: some View {
        VStack(alignment: .center) {
            WebImage(url: URL(string: movie.img))
                .resizable()
                .placeholder {
                    ProgressView()
                }
                .indicator(.activity)
                .transition(.fade)
                .scaledToFit()
                .cornerRadius(20)
            Spacer()

            Text(movie.title)
                .font(.body)
                .fontWeight(.medium)
                .padding(.bottom, 3)
            Spacer()
        }
    }
}
