var BasicDataGrid = DataGrid;

DataGrid = function(header,ds){

	this.base = new BasicDataGrid(header,ds);	

	this.base.getRowsDisplayBase = this.base.getRowsDisplay;
	this.base.paging = "enabled";
	this.base.pageSize = 5;
	this.base.currentPage = 1;

	this.base.maxPages = function(){
		return Math.ceil(this.dataSource.length / this.pageSize);
	}

	this.setPageSize = function(ps){
		this.base.pageSize = ps;
	}

	this.base.getRowsDisplay = function(){

		if(this.paging != "enabled"){
			return this.getRowsDisplayBase();
		}

		var html = '';

		for(var i=this.getStartIndex();i < this.getEndIndex() && i < this.dataSource.length;i++){
			html+= '<tr>';
			for(var j=0; j < this.dataSource[i].length; j++){
				html+= '<td>' + this.dataSource[i][j] + '</td>';
			}
			html+= '</tr>';
		}

		return html;		
	}

	this.base.getStartIndex = function(){
		return (this.currentPage-1)*this.pageSize;
	}

	this.base.getEndIndex = function(){
		return this.currentPage*this.pageSize;
	}

}

DataGrid.prototype.display = function(id){
	this.displayElementId = id;
	this.base.display(id);
	this.displayPager(id);
}

DataGrid.prototype.displayPager = function(id){
	if(this.base.paging == 'enabled'){
		var html = '<div class="gridPager"> <a href="#" onclick="grid_previous()">Previous</a> <a href="#" onclick="grid_next()">Next</a> </div>';
		document.getElementById(id).innerHTML += html;
	}
}

DataGrid.prototype.nextPage = function(){
	if(this.base.currentPage < this.base.maxPages()){
		this.base.currentPage++;
	}
	this.display(this.displayElementId);
}

DataGrid.prototype.previousPage = function(){
	if(this.base.currentPage > 1){
		this.base.currentPage--;
	}
	this.display(this.displayElementId);
}

