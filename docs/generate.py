# -*- coding: UTF-8 -*-
import os
import time
import sys

def listAllMarkdownFiles(root_dir):
	# find all markdown files from current dir or subdir

	# convert workspace
	os.chdir(root_dir)
	for dir in os.listdir('.'):
		# ignore hidden file
		if dir.startswith('.'):
			continue
		if os.path.isfile(dir):
			if dir.endswith('.md') and not dir.startswith("目录"):
				birthtime = os.stat(dir).st_mtime
				files.append((birthtime, root_dir + dir, dir))
		elif os.path.isdir(dir):
			listAllMarkdownFiles(dir + "/")

	# convert to last directory if it is not root directory
	if root_dir != './':
		os.chdir('..')

# convert to current working directory
path = os.path.dirname(sys.argv[0])
print(path)
os.chdir(path)

# collect markdown files in current directory
files = []
listAllMarkdownFiles('./')
files = sorted(files, key=lambda file:file[0], reverse=True)

# write to 目录 file
with open('./目录.md', 'w',encoding='utf-8') as datafile:
	datafile.writelines("# 日记\n\n")
	if len(files) != 0:
		last_file_time = None
	for file in files:
		file_time = time.gmtime(file[0])
		if last_file_time is None or file_time.tm_year != last_file_time.tm_year or file_time.tm_mon != last_file_time.tm_mon:

			# English version
			# datafile.writelines('## {}\n\n'.format(time.strftime("%B %Y",file_time)))
			# 中文版本
			datafile.writelines('## {}年{}月\n\n'.format(file_time.tm_year, file_time.tm_mon))

			last_file_time = file_time
		datafile.writelines('[{}——{}]({})\n\n'.format(file[2][:-3], time.strftime("%A, %Y-%m-%d, %H:%M:%S", file_time), file[1]))
