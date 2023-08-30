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
            
            if #available(iOS 15.0, *) {
                AsyncImage(url: URL(string:movie.img))
                    .frame(width: 500, height: 200)
                      .mask(RoundedRectangle(cornerRadius: 16))
            } else {
                // Fallback on earlier versions
            }
            
            Spacer()
            
            Text(movie.title)
                .font(.body)
                .fontWeight(.medium)
                .padding(.bottom, 3)
            
            Spacer()

        }
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
