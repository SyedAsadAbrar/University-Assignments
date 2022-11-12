function DataGrid(header,ds){
	this.header = header;
	this.dataSource = ds;

	this.display = function(divId){
		var html = '<table class="grid">';
		html += this.getHeaderDisplay();
		html += this.getRowsDisplay();
		html += '</table>';

		document.getElementById(divId).innerHTML = html;
	}

	this.getHeaderDisplay = function(){
		var html = '<tr>';
		for(var i=0; i < this.header.length;i++){
			html+= '<th>' + this.header[i] + '</th>';
		}
		html+= '</tr>';
		return html;
	}

	this.getRowsDisplay = function(){
		var html = '';
		for(var i=0; i < this.dataSource.length;i++){
			html+= '<tr>';
			for(var j=0; j < this.dataSource[i].length; j++){
				html+= '<td>' + this.dataSource[i][j] + '</td>';
			}
			html+= '</tr>';
		}
		return html;			
	}

}

