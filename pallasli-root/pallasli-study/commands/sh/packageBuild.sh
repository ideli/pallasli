javafxpackager -createjar -appclass Test -srcdir classes -outdir archive -outfile Test.jar

javafxpackager -deploy -appclass Test -native image -srcdir archive -outdir deploy -outfile Test