function img_auto_size(oldimg,maxSize,openNewWindow){
	var newimg = new Image();
	newimg.src = oldimg.src;
	if (newimg.width > 0 && newimg.height > 0) {
		if (newimg.width > maxSize){ 
			oldimg.width = maxSize;
			oldimg.height = (newimg.height * maxSize) / newimg.width;
			oldimg.onmouseover = function() {
				this.style.cursor= "hand";
			};
			oldimg.onmouseout = function() {
				this.style.cursor="";
			};
			if(openNewWindow){
				oldimg.onclick = function() {
					window.open(this.src, '_blank');
				};
			}
		} else {
			oldimg.width = newimg.width; 
			oldimg.height = newimg.height;
		}
		oldimg.alt = "点击查看原始大小 " + newimg.width + " x " + newimg.height;
	}
} 
