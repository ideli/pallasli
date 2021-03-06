module.exports = function(grunt) {

	// Project configuration.
	grunt
			.initConfig({
				// 引入package.json文件信息
				pkg : grunt.file.readJSON('package.json'),

				// 合并文件
				concat : {
					options : {
						// 定义一个用于插入合并输出文件之间的字符
						separator : ';'
					},
					dist : {
						// 将要被合并的文件
						src : [ 'src/**/*.js' ],
						// 合并后的JS文件的存放位置
						dest : 'dist/<%= pkg.name %>.js'
					}
				},

				// 压缩文件
				uglify : {
					options : {
						// 此处定义的banner注释将插入到输出文件的顶部
						banner : '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
					},
					dist : {
						files : {
							'dist/<%= pkg.name %>.min.js' : [ '<%= concat.dist.dest %>' ]
						}
					}
				},
				// 测试文件
				qunit : {
					files : [ 'test/**/*.html' ]
				},
				// 代码验证规则
				jshint : {
					// define the files to lint
					files : [ 'gruntfile.js', 'src/**/*.js', 'test/**/*.js' ],
					// configure JSHint (documented at
					// http://www.jshint.com/docs/)
					options : {
						// more options here if you want to override JSHint
						// defaults
						globals : {
							jQuery : true,
							console : true,
							module : true
						}
					}
				},
				watch : {
					files : [ '<%= jshint.files %>' ],
					tasks : [ 'jshint', 'qunit' ]
				}
			});

	// 加载包含 "uglify" 任务的插件。
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-qunit');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-concat');
	// 在命令行上输入"grunt test"，test task就会被执行。
	grunt.registerTask('test', [ 'jshint', 'qunit' ]);

	// 只需在命令行上输入"grunt"，就会执行default task
	grunt.registerTask('default', [ 'jshint', 'qunit', 'concat', 'uglify' ]);

};