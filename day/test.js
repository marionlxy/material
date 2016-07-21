var arr1 = [1, 2, 2, 2, 3, 3, 3, 4, 5, 6],
	arr2 = [];
for (var i = 0, len = arr1.length; i < len; i++) {
	if (arr2.indexOf(arr1[i]) < 0) {
		arr2.push(arr1[i]);
	}
}
document.write(arr2); // 1,2,3,4,5,6