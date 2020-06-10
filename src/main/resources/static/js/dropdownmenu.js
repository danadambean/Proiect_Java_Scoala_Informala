function choice(t){
	var a = ["BAR", "RESTAURANT", "CAFE", "FAST FOOD", "PASTRY DESSERTS"];
	var b = ["PARK", "MUSEUM", "ZOO", "HISTORICAL LANDMARKS", "NATURAL LANDMARKS"];
	var c = ["HOTEL", "MOTEL", "HOSTEL", "GUEST HOUSE", "CAMPING", "CABIN"];
	 s = document.getElementById('second');
	var sl = s.options.length;
	for(var i = sl-1; i >= 0 ; i--) { s.options[i] = null; }
	if(t.value != 0){
		var z;
		switch (t.value) {
            case "FOODDRINK": z = a; break
			case "ATTRACTIONS" : z = b; break;
			case "LODGING" : z = c; break;
			default : alert('Invalid entry'); break;
		}

		var l = z.length;
		for(i = 0; i < l; i++ ) { s.options[i] = new Option(z[i],z[i],false,false); }
	}
}

