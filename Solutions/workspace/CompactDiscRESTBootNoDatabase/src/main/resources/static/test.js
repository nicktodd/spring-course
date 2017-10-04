/**
 * Created by Nick Todd on 20/10/2015.
 */
var CompactDisc = function(title, artist, tracks, price) {
    this.title = title;
    this.artist = artist;
    this.price = price;
    this.tracks = tracks;

}

$(document).ready(function() {

    $("#addCDButton").click(function() {
        var cd = new CompactDisc("Dance of Death", "Iron Maiden", 12, 14.99);

        $.ajax({
            type: "POST",
            url: "compactdiscs",
            success: function () {
                alert("CD added");
            },
            data: JSON.stringify(cd),
            contentType: "application/json"

        });
    });

    $.ajax({
        type: "GET",
        url: "compactdiscs",

        success: function (cdList) {
            $.each(cdList, function (index, value) {
                $("#cdHolder").append("<li>" + value.title + " " + value.artist + "</li>");
            });
        }
    });
    
    $("#addCdBtn").click(function() {
        //var cd = new CompactDisc("Dance of Death", "Iron Maiden", 12, 14.99);
    	
    	var title = $("#titleField").val();
    	var artist = $("#artistField").val();
    	var price = $("#priceField").val();
    	var tracks = $("#tracksField").val();
    	
    	var newCD = new CompactDisc(title,artist,tracks,price);
    	
    	
    	
        $.ajax({
            type: "POST",
            url: "compactdiscs",
            success: function () {
                alert("CD added");
            },
            data: JSON.stringify(newCD),
            contentType: "application/json"

        });
    });
    
    
});


